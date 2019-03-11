package pe.com.gadolfolozano.mymovie.data.remote;

import android.arch.lifecycle.LiveData;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;

import java.util.List;

public interface ApiHelper {
    LiveData<MovieWrapperResponse> obtainMovie(String movieName);

    LiveData<List<MovieWrapperResponse>> obtainMovies(List<String> movies);
}
