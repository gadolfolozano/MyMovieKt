package pe.com.gadolfolozano.mymovie.ui.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.ActivityMainBinding
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import pe.com.gadolfolozano.mymovie.ui.login.LoginActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    @Inject
    lateinit var mMainViewModel: MainViewModel

    var mBinding: ActivityMainBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = mMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMainViewModel.navigator = this

        mBinding = getViewDataBinding()
        mBinding?.btnLogout?.setOnClickListener {
            showLoading()
            mMainViewModel.logout().observe(this,
                Observer<BaseResponseModel> { response ->
                    if (response?.status == BaseResponseModel.STATUS_SUCCESS) {
                        hideLoading()
                        openLogin()
                    }
                })
        }
    }

    override fun openLogin() {
        val intent = LoginActivity.newIntent(this@MainActivity)
        startActivity(intent)
        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
