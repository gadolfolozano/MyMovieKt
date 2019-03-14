package pe.com.gadolfolozano.mymovie.ui.search

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.data.remote.util.StateData
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

        searchViewModel.moviesFound.observe(this, Observer { response ->
            response.let {
                if (it?.status == StateData.STATE_LOADING) {
                    showLoading()
                } else if (it?.status == StateData.STATE_SUCCESS) {
                    hideLoading()
                } else if (it?.status == StateData.STATE_ERROR) {
                    hideLoading()
                }
            }
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