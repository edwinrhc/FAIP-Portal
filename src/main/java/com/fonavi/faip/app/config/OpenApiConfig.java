package com.fonavi.faip.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI OpenApiConfig() {
        return new OpenAPI()
                .info(new Info()
                    .title("FAIP Portal API")
                    .version("1.0.0")
                    .description("API REST para la gestión de solicitudes de acceso a la información pública")
                    .termsOfService("htts://www.gob.pe")
                    .contact(new Contact()
                            .name("Equipo de Desarrollo")
                            .email("soporte@example.pe")
                            .url("https://www.example.pe"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );

    }

}
