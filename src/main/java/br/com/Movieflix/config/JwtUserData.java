package br.com.Movieflix.config;

import lombok.Builder;

@Builder
public record JwtUserData(Long id, String name, String email) {
}
