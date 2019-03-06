package pe.com.gadolfolozano.mymovie.data.remote.util;

import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import retrofit2.Call;

public class MoviesService extends ServiceBase<MovieWrapperResponse> {

    private String movie;

    public MoviesService(String movie) {
        this.movie = movie;
    }

    @Override
    protected Call<MovieWrapperResponse> createCall() {
        return getAPIInterface().obtainMovie("7d89664d", movie);
    }
}
