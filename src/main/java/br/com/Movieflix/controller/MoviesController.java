package br.com.Movieflix.controller;
import br.com.Movieflix.controller.request.MovieRequest;
import br.com.Movieflix.controller.response.MovieResponse;
import br.com.Movieflix.entity.Movie;
import br.com.Movieflix.mapper.MovieMapper;
import br.com.Movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MoviesController {
    private final MovieService movieService;

    @GetMapping()
    public ResponseEntity<List<MovieResponse>> getAll () {
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @PostMapping()
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));
    }
}
