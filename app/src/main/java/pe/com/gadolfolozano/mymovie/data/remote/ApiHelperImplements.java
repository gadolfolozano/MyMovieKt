package pe.com.gadolfolozano.mymovie.data.remote;

import android.arch.lifecycle.LiveData;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.data.remote.util.MovieRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ApiHelperImplements implements ApiHelper {


    @Inject
    public ApiHelperImplements() {
        //Do nothing
    }

    @Override
    public LiveData<MovieWrapperResponse> obtainMovie(String movieName) {
        MovieRepository movieRepository = new MovieRepository();
        return movieRepository.getMovie(movieName);
    }

    @Override
    public LiveData<List<MovieWrapperResponse>> obtainMovies(List<String> movies) {
        MovieRepository movieRepository = new MovieRepository();
        return movieRepository.getMovies(movies);
    }
}
