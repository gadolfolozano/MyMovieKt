package pe.com.gadolfolozano.mymovie.data.remote.util;

import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;

public class MovieRepository {
    private final ApiInterface apiInterface;

    public MovieRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public RetrofitLiveData<MovieWrapperResponse> getMovie(String movieName) {
        return new RetrofitLiveData<>(apiInterface.obtainMovie("7d89664d", movieName));
    }
}
