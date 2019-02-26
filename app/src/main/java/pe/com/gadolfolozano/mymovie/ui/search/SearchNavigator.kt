package pe.com.gadolfolozano.mymovie.ui.search

import pe.com.gadolfolozano.mymovie.model.MovieModel

interface SearchNavigator {
    fun openMain()

    fun onDeleteMovie(movieModel: MovieModel, adapterPosition: Int)

    fun hideKeyboard()
}