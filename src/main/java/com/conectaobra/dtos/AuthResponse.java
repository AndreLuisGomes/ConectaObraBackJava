package com.conectaobra.dtos;

public record AuthResponse(String nome,
                           String accessToken,
                           String refreshToken,
                           String role) {
}
