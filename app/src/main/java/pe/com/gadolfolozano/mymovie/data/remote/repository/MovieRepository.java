package pe.com.gadolfolozano.mymovie.data.remote.repository;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse;
import pe.com.gadolfolozano.mymovie.data.remote.mapper.MovieMapper;
import pe.com.gadolfolozano.mymovie.data.remote.util.*;
import pe.com.gadolfolozano.mymovie.data.wrapper.State;
import pe.com.gadolfolozano.mymovie.data.wrapper.StateData;
import pe.com.gadolfolozano.mymovie.model.MovieWrapperModel;
import pe.com.gadolfolozano.mymovie.model.SearchMoviesModel;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MovieRepository {
    private final ApiInterface apiInterface;
    private final MovieMapper movieMapper;

    @Inject
    public MovieRepository(ApiInterface apiInterface, MovieMapper movieMapper) {
        this.apiInterface = apiInterface;
        this.movieMapper = movieMapper;
    }

    public LiveData<StateData<MovieWrapperModel>> getMovie(final String movieName) {
        return Transformations.map(new RetrofitLiveData<>(apiInterface.obtainMovie(Constants.API_KEY, movieName)), new Function<StateData<MovieWrapperResponse>, StateData<MovieWrapperModel>>() {
            @Override
            public StateData<MovieWrapperModel> apply(StateData<MovieWrapperResponse> stateData) {
                StateData<MovieWrapperModel> movieModelStateData = new StateData<>();
                movieModelStateData.setState(stateData.getState());
                movieModelStateData.setData(movieMapper.toModel(stateData.getData()));
                return movieModelStateData;
            }
        });
    }

    public LiveData<StateData<SearchMoviesModel>> getMovies(List<String> movies) {
        final MediatorLiveData<StateData<SearchMoviesModel>> merged = new MediatorLiveData<>();

        List<LiveData<StateData<MovieWrapperModel>>> sources = new ArrayList<>();
        for (String movie : movies) {
            sources.add(getMovie(movie));
        }

        merged.setValue(new StateData<SearchMoviesModel>().loading());
        addSources(merged, sources);

        return merged;
    }

    private SearchMoviesModel mergeLatestMovies(MediatorLiveData<StateData<SearchMoviesModel>> merged, MovieWrapperModel movieWrapperResponse) {
        SearchMoviesModel searchMoviesModel = new SearchMoviesModel();
        List<MovieWrapperModel> mergedMovies;
        if (merged.getValue() != null && merged.getValue().getData() != null && merged.getValue().getData().getMovieWrapperModels() != null) {
            mergedMovies = merged.getValue().getData().getMovieWrapperModels();
        } else {
            mergedMovies = new ArrayList<>();
        }
        mergedMovies.add(movieWrapperResponse);
        searchMoviesModel.setMovieWrapperModels(mergedMovies);
        return searchMoviesModel;
    }

    private void addSource(final MediatorLiveData<StateData<SearchMoviesModel>> merged, final LiveData<StateData<MovieWrapperModel>> movie, final List<LiveData<StateData<MovieWrapperModel>>> sources) {
        merged.addSource(movie, new Observer<StateData<MovieWrapperModel>>() {
            @Override
            public void onChanged(@Nullable StateData<MovieWrapperModel> stateData) {
                if (stateData != null && stateData.getState() != null && stateData.getState().getStatus() == State.STATE_SUCCESS) {
                    SearchMoviesModel searchMoviesModel = mergeLatestMovies(merged, stateData.getData());
                    StateData<SearchMoviesModel> stateDataResult = new StateData<>();
                    stateDataResult.setData(searchMoviesModel);
                    stateDataResult.setState(searchMoviesModel.getMovieWrapperModels() != null && searchMoviesModel.getMovieWrapperModels().size() == sources.size() ? new State().success() : new State().loading());
                    merged.setValue(stateDataResult);
                    merged.removeSource(movie);
                } else if (stateData != null && stateData.getState() != null && stateData.getState().getStatus() == State.STATE_ERROR) {
                    StateData<SearchMoviesModel> errorState = new StateData<>();
                    if (merged.getValue() != null) {
                        errorState = merged.getValue();
                        errorState.setState(stateData.getState());
                    } else {
                        errorState.setState(stateData.getState());
                    }
                    merged.setValue(errorState);
                    for (LiveData<StateData<MovieWrapperModel>> source : sources) {
                        merged.removeSource(source);
                    }
                }
            }
        });
    }

    private void addSources(final MediatorLiveData<StateData<SearchMoviesModel>> merged, List<LiveData<StateData<MovieWrapperModel>>> sources) {
        for (LiveData<StateData<MovieWrapperModel>> source : sources) {
            addSource(merged, source, sources);
        }
    }
}
