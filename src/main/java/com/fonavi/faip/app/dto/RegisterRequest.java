package com.fonavi.faip.app.dto;

public record RegisterRequest(
        String username,
        String password,
        String role // "ADMIN" or "USER""
) {}
