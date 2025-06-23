package br.com.Movieflix.repository;

import br.com.Movieflix.entity.Category;
import br.com.Movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories(List<Category> categories);

    List<Movie> findTop4ByOrderByRatingDesc();


}
