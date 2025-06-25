package br.com.Movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record StreamingRequest(@NotEmpty(message = "nome do streaming obrigatorio.") String name) {
}
