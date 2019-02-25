package pe.com.gadolfolozano.mymovie.ui.search

import android.databinding.ObservableField
import android.util.Log
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel


class SearchItemViewModel(movieModel: MovieModel) : BaseViewModel<SearchNavigator>() {

    val movie = ObservableField<MovieModel>(movieModel)

    fun onDeleteItemClicked() {
        Log.i("DATABINDING ", "DATABINDING onDeleteItemClicked ")
    }
}