package pe.com.gadolfolozano.mymovie.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.util.Log
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
    //val moviesFound = MutableLiveData<MovieWrapperResponse>()

    val moviesToSearch = MutableLiveData<List<String>>()

    val moviesFound = Transformations.switchMap(moviesToSearch) { movies ->
        dataManager.obtainMovie(movies[0])
    }

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

    fun getMovieList(movieList: List<String>): LiveData<List<MovieWrapperResponse>> {
        val movie1 = dataManager.obtainMovie(movieList[0])
        val movie2 = dataManager.obtainMovie(movieList[1])
        val movie3 = dataManager.obtainMovie(movieList[2])
        val movie4 = dataManager.obtainMovie(movieList[3])
        val movie5 = dataManager.obtainMovie(movieList[4])

        val result = MediatorLiveData<List<MovieWrapperResponse>>()

        result.addSource(movie1) { response ->
            val combinedData = combineLatestData(movie1, movie2, movie3, movie4, movie5)
            if (combinedData != null) {
                result.value = combinedData
            }
        }

        result.addSource(movie2) { response ->
            val combinedData = combineLatestData(movie1, movie2, movie3, movie4, movie5)
            if (combinedData != null) {
                result.value = combinedData
            }
        }

        result.addSource(movie3) { response ->
            val combinedData = combineLatestData(movie1, movie2, movie3, movie4, movie5)
            if (combinedData != null) {
                result.value = combinedData
            }
        }

        result.addSource(movie4) { response ->
            val combinedData = combineLatestData(movie1, movie2, movie3, movie4, movie5)
            if (combinedData != null) {
                result.value = combinedData
            }
        }

        result.addSource(movie5) { response ->
            val combinedData = combineLatestData(movie1, movie2, movie3, movie4, movie5)
            if (combinedData != null) {
                result.value = combinedData
            }
        }

        return result
    }

    private fun combineLatestData(
        movie1: LiveData<MovieWrapperResponse>?,
        movie2: LiveData<MovieWrapperResponse>?,
        movie3: LiveData<MovieWrapperResponse>?,
        movie4: LiveData<MovieWrapperResponse>?,
        movie5: LiveData<MovieWrapperResponse>?
    ): List<MovieWrapperResponse>? {
        if (movie1?.value != null && movie2?.value != null && movie3?.value != null && movie4?.value != null && movie5?.value != null) {
            val resultList = ArrayList<MovieWrapperResponse>()
            resultList.add(movie1.value!!)
            resultList.add(movie2.value!!)
            resultList.add(movie3.value!!)
            resultList.add(movie4.value!!)
            resultList.add(movie5.value!!)
            return resultList
        }
        return null
    }
}