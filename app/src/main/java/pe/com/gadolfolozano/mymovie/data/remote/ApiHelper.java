package pe.com.gadolfolozano.mymovie.data.remote;

import android.arch.lifecycle.LiveData;
import pe.com.gadolfolozano.mymovie.data.wrapper.StateData;
import pe.com.gadolfolozano.mymovie.model.MovieWrapperModel;
import pe.com.gadolfolozano.mymovie.model.SearchMoviesModel;

import java.util.List;

public interface ApiHelper {
    LiveData<StateData<MovieWrapperModel>> obtainMovie(String movieName);

    LiveData<StateData<SearchMoviesModel>> obtainMovies(List<String> movies);
}
