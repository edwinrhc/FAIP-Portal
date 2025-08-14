package com.fonavi.faip.app.service;

import com.fonavi.faip.app.entity.Solicitud;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Override
    public void enviarAvisoVencimiento(Solicitud solicitud) {

        System.out.println("Enviando aviso de vencimiento a: " + solicitud.getEmail());
    }
}
