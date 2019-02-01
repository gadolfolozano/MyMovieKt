package pe.com.gadolfolozano.mymovie.ui.splash

import android.arch.lifecycle.Observer
import android.os.Bundle
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.ActivitySplashBinding
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import pe.com.gadolfolozano.mymovie.ui.login.LoginActivity
import pe.com.gadolfolozano.mymovie.ui.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    @Inject
    lateinit var mSplashViewModel: SplashViewModel

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() = mSplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSplashViewModel.navigator = this
        mSplashViewModel.getCurrentUser().observe(this,
            Observer<LoginResponseModel> { loginResponseModel ->
                when (loginResponseModel?.status) {
                    BaseResponseModel.STATUS_SUCCESS -> {
                        openMainActivity()
                    }
                    BaseResponseModel.STATUS_ERROR -> {
                        openLoginActivity()
                    }
                }
            })
    }

    override fun openLoginActivity() {
        val intent = LoginActivity.newIntent(this@SplashActivity)
        startActivity(intent)
        finish()
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this@SplashActivity)
        startActivity(intent)
        finish()
    }
}