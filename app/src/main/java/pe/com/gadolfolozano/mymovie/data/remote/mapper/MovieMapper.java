package pe.com.gadolfolozano.mymovie.data.remote.mapper;

import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieResponse;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.model.MovieModel;
import pe.com.gadolfolozano.mymovie.model.MovieWrapperModel;
import pe.com.gadolfolozano.mymovie.model.SearchMoviesModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MovieMapper {

    @Inject
    public MovieMapper() {
        //Do nothing
    }

    public MovieModel toModel(MovieResponse entity) {
        MovieModel model = new MovieModel();
        if (entity != null) {
            model.setTitle(entity.getTitle());
        }
        return model;
    }

    public MovieWrapperModel toModel(MovieWrapperResponse entity) {
        MovieWrapperModel model = new MovieWrapperModel();
        List<MovieModel> movies = new ArrayList<>();
        if (entity != null && entity.getSearch() != null) {
            for (MovieResponse movieResponse : entity.getSearch()) {
                movies.add(toModel(movieResponse));
            }
        }
        model.setMovies(movies);
        return model;
    }

    public SearchMoviesModel toModel(List<MovieWrapperResponse> entities) {
        SearchMoviesModel model = new SearchMoviesModel();
        List<MovieWrapperModel> movieWrapperModels = new ArrayList<>();
        if (entities != null) {
            for (MovieWrapperResponse movieWrapperResponse : entities) {
                movieWrapperModels.add(toModel(movieWrapperResponse));
            }
        }
        model.setMovieWrapperModels(movieWrapperModels);
        return model;
    }
}
