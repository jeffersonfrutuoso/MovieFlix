package br.com.Movieflix.service;

import br.com.Movieflix.entity.Category;
import br.com.Movieflix.entity.Movie;
import br.com.Movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> findByID (Long id) {
        return movieRepository.findById(id);
    }

    public void deletar(Long id) {
        movieRepository.deleteById(id);
    }
}
