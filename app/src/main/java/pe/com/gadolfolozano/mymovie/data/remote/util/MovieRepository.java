package pe.com.gadolfolozano.mymovie.data.remote.util;

import android.arch.lifecycle.LiveData;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;

public class MovieRepository {
    private final ApiInterface apiInterface;

    public MovieRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public LiveData<MovieWrapperResponse> getMovie(String movieName) {
        return new RetrofitLiveData<>(apiInterface.obtainMovie("7d89664d", movieName));
    }
}
