package br.com.Movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "nome da categoria obrigatorio.") String name) {
}
