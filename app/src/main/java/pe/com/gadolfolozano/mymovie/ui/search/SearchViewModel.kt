package pe.com.gadolfolozano.mymovie.ui.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel


class SearchViewModel(dataManager: DataManager) : BaseViewModel<SearchNavigator>(dataManager) {
    val showAddMovieView = ObservableBoolean(true)
    val movies = ObservableArrayList<MovieModel>()

    fun addMovie(movieModel: MovieModel) {
        movies.add(movieModel)
    }


}