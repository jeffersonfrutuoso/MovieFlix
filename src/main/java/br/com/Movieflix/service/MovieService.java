package br.com.Movieflix.service;
import br.com.Movieflix.entity.Category;
import br.com.Movieflix.entity.Movie;
import br.com.Movieflix.entity.Streaming;
import br.com.Movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private  final StreamingService streamingService;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByCategory(Long categoryById) {
            return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryById).build()));
    }

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category ->
                categoryService.findByID(category.getId()).ifPresent(categoriesFound::add)
        );
        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming ->
                streamingService.findStreamingById(streaming.getId()).ifPresent(streamingsFound::add)
        );
        return streamingsFound;
    }

    public Optional<Movie> update(Long movieId, Movie updatedMovie){
        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()){

            List<Category> categories = this.findCategories(updatedMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updatedMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setRating(updatedMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);

            return  Optional.of(movie);
        }
        return Optional.empty();
    }

    public Optional<Movie> findByID (Long id) {
        return movieRepository.findById(id);
    }

    public void deletar(Long id) {
        movieRepository.deleteById(id);
    }
}
