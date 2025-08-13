package com.fonavi.faip.app.controller;


import com.fonavi.faip.app.dto.SolicitudForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class SolicitudController {

    @GetMapping("/solicitud")
    public String crear(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new SolicitudForm());
        }
        model.addAttribute("appName", "Portal AIP");
        return "solicitud";
    }

    @PostMapping("/solicitud")
    public String registrar(@Valid @ModelAttribute("form") SolicitudForm form,
                            BindingResult br,
                            @RequestParam(value = "files", required = false) List<MultipartFile> files,
                            RedirectAttributes ra) {
        if (br.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.form", br);
            ra.addFlashAttribute("form", form);
            return "redirect:/solicitud";
        }

        // TODO: guardar en BD y archivos (validar tamaños, tipos)
        // Simulación: generar código
        String codigo = "AIP-2025-000001";

        ra.addFlashAttribute("ok",
                "Solicitud registrada correctamente. Código: " + codigo);
        return "redirect:/seguimiento?codigo=" + codigo;
    }

    @GetMapping("/seguimiento")
    public String seguimiento(@RequestParam(required = false) String codigo, Model model) {
        model.addAttribute("appName", "Portal AIP");
        model.addAttribute("codigo", codigo);
        return "seguimiento"; // crea luego una vista simple
    }
}