package pe.com.gadolfolozano.mymovie.ui.search

import android.databinding.ObservableField
import android.databinding.ObservableInt
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel


class SearchItemViewModel(movieModel: MovieModel, position: Int) : BaseViewModel<SearchNavigator>() {

    val movie = ObservableField<MovieModel>(movieModel)
    val adapterPosition = ObservableInt(position)

    fun onDeleteItemClicked() {
        if (movie.get() != null) {
            navigator?.onDeleteMovie(movie.get()!!, adapterPosition.get())
        }
    }
}