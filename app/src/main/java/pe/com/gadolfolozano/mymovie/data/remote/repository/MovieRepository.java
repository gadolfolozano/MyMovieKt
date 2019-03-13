package pe.com.gadolfolozano.mymovie.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.data.remote.util.ApiInterface;
import pe.com.gadolfolozano.mymovie.data.remote.util.Constants;
import pe.com.gadolfolozano.mymovie.data.remote.util.RetrofitLiveData;
import pe.com.gadolfolozano.mymovie.data.remote.util.StateData;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MovieRepository {
    private final ApiInterface apiInterface;

    @Inject
    public MovieRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public LiveData<StateData<MovieWrapperResponse>> getMovie(String movieName) {
        return new RetrofitLiveData<>(apiInterface.obtainMovie(Constants.API_KEY, movieName));
    }

    public LiveData<List<MovieWrapperResponse>> getMovies(List<String> movies) {
        final MediatorLiveData<List<MovieWrapperResponse>> merged = new MediatorLiveData<>();

        final LiveData<StateData<MovieWrapperResponse>> movie1 = getMovie(movies.get(0));
        final LiveData<StateData<MovieWrapperResponse>> movie2 = getMovie(movies.get(1));
        final LiveData<StateData<MovieWrapperResponse>> movie3 = getMovie(movies.get(2));
        final LiveData<StateData<MovieWrapperResponse>> movie4 = getMovie(movies.get(3));
        final LiveData<StateData<MovieWrapperResponse>> movie5 = getMovie(movies.get(4));

        merged.addSource(movie1, new Observer<StateData<MovieWrapperResponse>>() {
            @Override
            public void onChanged(@Nullable StateData<MovieWrapperResponse> stateData) {
                if (stateData != null && stateData.getStatus() == StateData.STATE_SUCCESS) {
                    merged.setValue(mergeLatestMovies(merged, stateData.getData()));
                    merged.removeSource(movie1);
                }
            }
        });

        merged.addSource(movie2, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLatestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie2);
            }
        });

        merged.addSource(movie3, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLatestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie3);
            }
        });

        merged.addSource(movie4, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLatestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie4);
            }
        });

        merged.addSource(movie5, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLatestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie5);
            }
        });

        return merged;
    }

    private List<MovieWrapperResponse> mergeLatestMovies(MediatorLiveData<List<MovieWrapperResponse>> merged, MovieWrapperResponse movieWrapperResponse) {
        List<MovieWrapperResponse> mergedList = merged.getValue() != null ? merged.getValue() : new ArrayList<MovieWrapperResponse>();
        mergedList.add(movieWrapperResponse);
        return mergedList;
    }
}
