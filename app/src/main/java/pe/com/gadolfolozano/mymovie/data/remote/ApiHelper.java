package pe.com.gadolfolozano.mymovie.data.remote;

import android.arch.lifecycle.LiveData;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.data.remote.util.StateData;

import java.util.List;

public interface ApiHelper {
    LiveData<StateData<MovieWrapperResponse>> obtainMovie(String movieName);

    LiveData<StateData<List<MovieWrapperResponse>>> obtainMovies(List<String> movies);
}
