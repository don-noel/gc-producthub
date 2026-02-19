package com.gchub.producthub.dto;

public record LoginResponse(
    String token,
    String email,
    String role
) {}