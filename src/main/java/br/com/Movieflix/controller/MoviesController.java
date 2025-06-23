package br.com.Movieflix.controller;
import br.com.Movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MoviesController {
    private final MovieService movieService;
}
