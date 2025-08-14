package com.fonavi.faip.app.service;

import com.fonavi.faip.app.entity.Solicitud;

public interface EmailService {

    void enviarAvisoVencimiento(Solicitud solicitud);

}
