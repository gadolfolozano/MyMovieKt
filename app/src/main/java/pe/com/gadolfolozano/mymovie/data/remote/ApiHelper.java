package pe.com.gadolfolozano.mymovie.data.remote;

import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.data.remote.util.RetrofitLiveData;

public interface ApiHelper {
    RetrofitLiveData<MovieWrapperResponse> obtainMovie(String movieName);
}
