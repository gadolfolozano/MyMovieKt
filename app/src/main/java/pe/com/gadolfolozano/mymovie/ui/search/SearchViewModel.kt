package pe.com.gadolfolozano.mymovie.ui.search

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse
import pe.com.gadolfolozano.mymovie.data.remote.util.RetrofitLiveData
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel
import pe.com.gadolfolozano.mymovie.ui.util.Constants


class SearchViewModel(val dataManager: DataManager) : BaseViewModel<SearchNavigator>() {
    val movies = ObservableArrayList<MovieModel>()
    val movieInput = ObservableField<String>(Constants.EMPTY_STRING)
    //val moviesFound = dataManager.obtainMovie("matrix")
    val moviesFound = MutableLiveData<MovieWrapperResponse>()

    fun onButtonAddClicked() {
        val movieInputValue = movieInput.get()
        if (movieInputValue != null) {
            movies.add(MovieModel(movieInputValue))
            movieInput.set(Constants.EMPTY_STRING)

            if (movies.size >= 5) {
                navigator?.hideKeyboard()
            }
        }
    }

    fun onButtonSearchClicked(): RetrofitLiveData<MovieWrapperResponse>? {
        isLoading.value = true
        //moviesFound.value = dataManager.obtainMovie(getMoviesToSearch().get(0))
        //moviesFound.value = dataManager.obtainMovie("Matrix").value
        return dataManager.obtainMovie(getMoviesToSearch().get(0))
    }

    private fun getMoviesToSearch(): List<String> {
        val moviesToSearchList = ArrayList<String>()
        for (movie in movies) {
            if (movie.title != null && !movie.title.isEmpty()) {
                moviesToSearchList.add(movie.title)
            }
        }
        return moviesToSearchList
    }
}