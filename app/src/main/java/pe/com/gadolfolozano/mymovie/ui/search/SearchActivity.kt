package pe.com.gadolfolozano.mymovie.ui.search

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.data.remote.entity.MovieWrapperResponse
import pe.com.gadolfolozano.mymovie.databinding.ActivitySearchBinding
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import pe.com.gadolfolozano.mymovie.ui.main.MainActivity
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(), SearchNavigator {

    @Inject
    lateinit var searchViewModel: SearchViewModel

    var binding: ActivitySearchBinding? = null

    var searchMovieAdapter: SearchMovieAdapter? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_search
    override val viewModel: SearchViewModel
        get() = searchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel.navigator = this

        binding = getViewDataBinding()
        binding?.viewModel = searchViewModel

        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        searchMovieAdapter = SearchMovieAdapter(this)
        binding?.recyclerView?.adapter = searchMovieAdapter

        searchViewModel.isLoading.observe(this, Observer<Boolean> { isLoading ->
            if (isLoading != null && isLoading) {
                //showLoading()
            } else {
                //hideLoading()
            }
        })

        searchViewModel.moviesFound.observe(this, Observer<MovieWrapperResponse> { response ->
            Log.i("holi", "response " + response)
        })

        val movieslook = ArrayList<String>()
        movieslook.add("Matrix")
        movieslook.add("back")
        movieslook.add("jack")
        movieslook.add("wallie")
        movieslook.add("chocolate")
        searchViewModel.getMovieList(movieslook).observe(this, Observer { movieList ->
            Log.i("holi", "movieList " + movieList)
        })
    }

    override fun openMain() {
        val intent = MainActivity.newIntent(this@SearchActivity)
        startActivity(intent)
        finish()
    }

    override fun onDeleteMovie(movieModel: MovieModel, adapterPosition: Int) {
        if (searchViewModel.movies.size > adapterPosition) {
            searchViewModel.movies.removeAt(adapterPosition)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }
}