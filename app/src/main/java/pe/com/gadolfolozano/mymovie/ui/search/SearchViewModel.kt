package pe.com.gadolfolozano.mymovie.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel
import pe.com.gadolfolozano.mymovie.ui.util.Constants


class SearchViewModel(val dataManager: DataManager) : BaseViewModel<SearchNavigator>() {
    val movies = ObservableArrayList<MovieModel>()
    val movieInput = ObservableField<String>(Constants.EMPTY_STRING)

    val moviesToSearch = MutableLiveData<List<String>>()

    val moviesFound = Transformations.switchMap(moviesToSearch) { movies ->
        dataManager.obtainMovies(movies)
    }

    fun onButtonAddClicked() {
        val movieInputValue = movieInput.get()
        if (movieInputValue != null) {
            val movieModel = MovieModel()
            movieModel.title = movieInputValue
            movies.add(movieModel)
            movieInput.set(Constants.EMPTY_STRING)

            if (movies.size >= 5) {
                navigator?.hideKeyboard()
            }
        }
    }

    fun onButtonSearchClicked() {
        isLoading.value = true
        moviesToSearch.value = getMoviesToSearch()
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