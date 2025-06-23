package br.com.Movieflix.mapper;

import br.com.Movieflix.controller.request.MovieRequest;
import br.com.Movieflix.controller.response.CategoryResponse;
import br.com.Movieflix.controller.response.MovieResponse;
import br.com.Movieflix.controller.response.StreamingResponse;
import br.com.Movieflix.entity.Category;
import br.com.Movieflix.entity.Movie;
import br.com.Movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest){
        List<Category> categories = movieRequest.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse (Movie movie){

        List<CategoryResponse> categoryResponses = movie.getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamingResponses = movie.getStreamings()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categoryResponses)
                .streamings(streamingResponses)
                .build();
    }
}
