package pe.com.gadolfolozano.mymovie.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.ActivitySearchBinding
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import pe.com.gadolfolozano.mymovie.ui.main.MainActivity
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(), SearchNavigator {

    @Inject
    lateinit var mSearchViewModel: SearchViewModel

    var mBinding: ActivitySearchBinding? = null

    var searchMovieAdapter: SearchMovieAdapter? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_search
    override val viewModel: SearchViewModel
        get() = mSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSearchViewModel.navigator = this

        mBinding = getViewDataBinding()
        mBinding?.viewModel = mSearchViewModel

        mBinding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        searchMovieAdapter = SearchMovieAdapter(ArrayList<MovieModel>())
        mBinding?.recyclerView?.adapter = searchMovieAdapter
    }

    override fun openMain() {
        val intent = MainActivity.newIntent(this@SearchActivity)
        startActivity(intent)
        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }
}