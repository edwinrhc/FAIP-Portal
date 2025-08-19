import com.fonavi.faip.app.dto.AuthResponse;
import com.fonavi.faip.app.dto.LoginRequest;
import com.fonavi.faip.app.entity.Role;
import com.fonavi.faip.app.entity.User;
import com.fonavi.faip.app.repository.UserRepository;
import com.fonavi.faip.app.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse login(LoginRequest request) {
        // Validación básica de entrada
        if (request.username() == null || request.password() == null ||
                request.username().trim().isEmpty() || request.password().trim().isEmpty()) {
            logger.warn("Intento de inicio de sesión con credenciales vacías");
            throw new InvalidCredentialsException("Las credenciales no pueden estar vacías");
        }

        try {
            // Buscar usuario por nombre de usuario
            User user = userRepository.findByUsername(request.username())
                    .orElseThrow(() -> new InvalidCredentialsException("Credenciales inválidas"));

            // Verificar contraseña
            if (!passwordEncoder.matches(request.password(), user.getPassword())) {
                logger.warn("Intento de inicio de sesión fallido para el usuario: {}", request.username());
                throw new InvalidCredentialsException("Credenciales inválidas");
            }

            // Obtener rol del usuario con verificación de seguridad
            Set<Role> roles = user.getRoles();
            if (roles == null || roles.isEmpty()) {
                logger.error("Usuario {} no tiene roles asignados", user.getUsername());
                throw new AuthorizationException("Usuario sin roles asignados");
            }

            String role = roles.iterator().next().toString(); // asumiendo que Role tiene un método toString() apropiado

            // Crear mapa de claims para el token
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", role);

            // Generar token JWT
            String token = jwtService.generateToken(user.getUsername(), claims);

            logger.info("Inicio de sesión exitoso para el usuario: {}", user.getUsername());
            return new AuthResponse(token, role);
        } catch (InvalidCredentialsException e) {
            // Importante: usamos el mismo mensaje para ambos casos (usuario no encontrado o contraseña incorrecta)
            // para no revelar información sensible
            throw e;
        } catch (Exception e) {
            logger.error("Error en el proceso de autenticación", e);
            throw new AuthenticationServiceException("Error en el servicio de autenticación");
        }
    }

    // Resto del código...
}