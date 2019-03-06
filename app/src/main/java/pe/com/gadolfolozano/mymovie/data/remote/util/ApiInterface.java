package pe.com.gadolfolozano.mymovie.data.remote.util;

import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/")
    Call<MovieWrapperResponse> obtainMovie(@Query("apikey") String apiKey, @Query("s") String movieName);
}
