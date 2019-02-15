package pe.com.gadolfolozano.mymovie.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.ActivitySearchBinding
import pe.com.gadolfolozano.mymovie.model.MovieModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import pe.com.gadolfolozano.mymovie.ui.main.MainActivity
import pe.com.gadolfolozano.mymovie.ui.util.AdapterListener
import pe.com.gadolfolozano.mymovie.ui.util.Constants
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
        searchMovieAdapter?.setAdapterListener(object : AdapterListener<MovieModel> {
            override fun onItemRemoved(dataSet: List<MovieModel>, item: MovieModel) {
                Toast.makeText(this@SearchActivity, "show " + true, Toast.LENGTH_SHORT).show()
                mSearchViewModel.showAddMovieView.set(true)
            }

            override fun onItemAdded(dataSet: List<MovieModel>, item: MovieModel) {
                Toast.makeText(this@SearchActivity, "show " + false, Toast.LENGTH_SHORT).show()
                mSearchViewModel.showAddMovieView.set(false)
            }
        })
        mBinding?.recyclerView?.adapter = searchMovieAdapter
        mBinding?.button?.setOnClickListener {
            onButtonAddClicked()
        }

    }

    fun onButtonAddClicked() {
        if (!mBinding?.editText?.text.isNullOrEmpty()) {
            mSearchViewModel.addMovie(MovieModel(mBinding?.editText?.text.toString()))
            mBinding?.editText?.setText(Constants.EMPTY_STRING)
        }
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