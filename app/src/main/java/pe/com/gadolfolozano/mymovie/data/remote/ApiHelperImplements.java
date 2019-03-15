package pe.com.gadolfolozano.mymovie.data.remote;

import android.arch.lifecycle.LiveData;
import pe.com.gadolfolozano.mymovie.data.remote.repository.MovieRepository;
import pe.com.gadolfolozano.mymovie.data.wrapper.StateData;
import pe.com.gadolfolozano.mymovie.model.MovieWrapperModel;
import pe.com.gadolfolozano.mymovie.model.SearchMoviesModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ApiHelperImplements implements ApiHelper {

    private final MovieRepository movieRepository;

    @Inject
    public ApiHelperImplements(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public LiveData<StateData<MovieWrapperModel>> obtainMovie(String movieName) {
        return movieRepository.getMovie(movieName);
    }

    @Override
    public LiveData<StateData<SearchMoviesModel>> obtainMovies(List<String> movies) {
        return movieRepository.getMovies(movies);
    }
}
