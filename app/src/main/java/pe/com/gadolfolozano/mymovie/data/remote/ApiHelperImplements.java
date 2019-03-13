package pe.com.gadolfolozano.mymovie.data.remote;

import android.arch.lifecycle.LiveData;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.data.remote.repository.MovieRepository;

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
    public LiveData<MovieWrapperResponse> obtainMovie(String movieName) {
        return movieRepository.getMovie(movieName);
    }

    @Override
    public LiveData<List<MovieWrapperResponse>> obtainMovies(List<String> movies) {
        return movieRepository.getMovies(movies);
    }
}
