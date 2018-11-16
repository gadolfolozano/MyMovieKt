package pe.com.gadolfolozano.mymovie.ui.splash

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.ActivitySplashBinding
import pe.com.gadolfolozano.mymovie.model.BaseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
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
        mSplashViewModel.dummyValue.observe(this, Observer<BaseModel> { baseModel ->
            if (baseModel != null && baseModel.status == BaseModel.STATUS_SUCCESS) {
                openMainActivity()
            } else {
                openLoginActivity()
            }
        })
    }

    override fun openLoginActivity() {
        TODO("not implemented")
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this@SplashActivity)
        startActivity(intent)
        finish()
    }
}