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

    public LiveData<StateData<List<MovieWrapperResponse>>> getMovies(List<String> movies) {
        final MediatorLiveData<StateData<List<MovieWrapperResponse>>> merged = new MediatorLiveData<>();

        List<LiveData<StateData<MovieWrapperResponse>>> sources = new ArrayList<>();
        for (String movie : movies) {
            sources.add(getMovie(movie));
        }

        merged.setValue(new StateData<List<MovieWrapperResponse>>().loading());
        addSources(merged, sources);

        return merged;
    }

    private List<MovieWrapperResponse> mergeLatestMovies(MediatorLiveData<StateData<List<MovieWrapperResponse>>> merged, MovieWrapperResponse movieWrapperResponse) {
        List<MovieWrapperResponse> mergedList = merged.getValue() != null && merged.getValue().getData() != null ? merged.getValue().getData() : new ArrayList<MovieWrapperResponse>();
        mergedList.add(movieWrapperResponse);
        return mergedList;
    }

    private void addSource(final MediatorLiveData<StateData<List<MovieWrapperResponse>>> merged, final LiveData<StateData<MovieWrapperResponse>> movie, final List<LiveData<StateData<MovieWrapperResponse>>> sources) {
        merged.addSource(movie, new Observer<StateData<MovieWrapperResponse>>() {
            @Override
            public void onChanged(@Nullable StateData<MovieWrapperResponse> stateData) {
                if (stateData != null && stateData.getStatus() == StateData.STATE_SUCCESS) {
                    List<MovieWrapperResponse> merdedList = mergeLatestMovies(merged, stateData.getData());
                    StateData<List<MovieWrapperResponse>> stateDataResult = new StateData<>();
                    stateDataResult.setData(merdedList);
                    stateDataResult.setStatus(merdedList.size() == sources.size() ? StateData.STATE_SUCCESS : StateData.STATE_LOADING);
                    merged.setValue(stateDataResult);
                    merged.removeSource(movie);
                } else if (stateData != null && stateData.getStatus() == StateData.STATE_ERROR) {
                    StateData<List<MovieWrapperResponse>> errorState;
                    if (merged.getValue() != null) {
                        errorState = merged.getValue();
                        errorState.setStatus(StateData.STATE_ERROR);
                        errorState.setError(stateData.getError());
                    } else {
                        errorState = new StateData<List<MovieWrapperResponse>>().error(stateData.getError());
                    }
                    merged.setValue(errorState);
                    for (LiveData source : sources) {
                        merged.removeSource(source);
                    }
                }
            }
        });
    }

    private void addSources(final MediatorLiveData<StateData<List<MovieWrapperResponse>>> merged, List<LiveData<StateData<MovieWrapperResponse>>> sources) {
        for (LiveData<StateData<MovieWrapperResponse>> source : sources) {
            addSource(merged, source, sources);
        }
    }
}
