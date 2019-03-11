package pe.com.gadolfolozano.mymovie.data.remote.util;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private final ApiInterface apiInterface;

    public MovieRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public LiveData<MovieWrapperResponse> getMovie(String movieName) {
        return new RetrofitLiveData<>(apiInterface.obtainMovie("7d89664d", movieName));
    }

    public LiveData<List<MovieWrapperResponse>> getMovies(List<String> movies) {
        final MediatorLiveData<List<MovieWrapperResponse>> merged = new MediatorLiveData<>();

        final LiveData<MovieWrapperResponse> movie1 = getMovie(movies.get(0));
        final LiveData<MovieWrapperResponse> movie2 = getMovie(movies.get(1));
        final LiveData<MovieWrapperResponse> movie3 = getMovie(movies.get(2));
        final LiveData<MovieWrapperResponse> movie4 = getMovie(movies.get(3));
        final LiveData<MovieWrapperResponse> movie5 = getMovie(movies.get(4));

        merged.addSource(movie1, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLastestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie1);
            }
        });

        merged.addSource(movie2, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLastestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie2);
            }
        });

        merged.addSource(movie3, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLastestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie3);
            }
        });

        merged.addSource(movie4, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLastestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie4);
            }
        });

        merged.addSource(movie5, new Observer<MovieWrapperResponse>() {
            @Override
            public void onChanged(@Nullable MovieWrapperResponse movieWrapperResponse) {
                merged.setValue(mergeLastestMovies(merged, movieWrapperResponse));
                merged.removeSource(movie5);
            }
        });

        return merged;
    }

    private List<MovieWrapperResponse> mergeLastestMovies(MediatorLiveData<List<MovieWrapperResponse>> merged, MovieWrapperResponse movieWrapperResponse) {
        List<MovieWrapperResponse> mergedList = merged.getValue() != null ? merged.getValue() : new ArrayList<MovieWrapperResponse>();
        mergedList.add(movieWrapperResponse);
        return mergedList;
    }
}
