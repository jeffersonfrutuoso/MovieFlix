package br.com.Movieflix.controller.request;

import br.com.Movieflix.entity.Category;
import br.com.Movieflix.entity.Streaming;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(@NotEmpty(message = "nome do filme obrigatorio.") String title,
                           String description,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate releaseDate,
                           double rating,
                           List<Long> categories,
                           List<Long> streamings) {
}
