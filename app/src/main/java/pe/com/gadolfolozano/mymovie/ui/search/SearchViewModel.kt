package pe.com.gadolfolozano.mymovie.ui.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel
import pe.com.gadolfolozano.mymovie.ui.util.Constants


class SearchViewModel(dataManager: DataManager) : BaseViewModel<SearchNavigator>(dataManager) {
    val movies = ObservableArrayList<MovieModel>()
    val movieInput = ObservableField<String>(Constants.EMPTY_STRING)

    fun addMovie(movieModel: MovieModel) {
        movies.add(movieModel)
    }

    fun onButtonAddClicked() {
        val movieInputValue = movieInput.get()
        if (movieInputValue != null) {
            movies.add(MovieModel(movieInputValue))
            movieInput.set(Constants.EMPTY_STRING)
        }
    }
}