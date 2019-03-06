package pe.com.gadolfolozano.mymovie.data.remote;

import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.data.remote.util.MovieRepository;
import pe.com.gadolfolozano.mymovie.data.remote.util.RetrofitLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHelperImplements implements ApiHelper {


    @Inject
    public ApiHelperImplements() {
        //Do nothing
    }

    @Override
    public RetrofitLiveData<MovieWrapperResponse> obtainMovie(String movieName) {
        MovieRepository movieRepository = new MovieRepository();
        return movieRepository.getMovie(movieName);
    }
}