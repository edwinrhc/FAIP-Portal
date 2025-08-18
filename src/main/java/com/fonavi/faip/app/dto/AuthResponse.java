package com.fonavi.faip.app.dto;

public record AuthResponse(
        String token,
        String role
) {}
