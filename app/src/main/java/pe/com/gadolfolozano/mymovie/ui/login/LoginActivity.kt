package pe.com.gadolfolozano.mymovie.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.ActivityLoginBinding
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import pe.com.gadolfolozano.mymovie.ui.login.createaccount.CreateAccountFragment
import pe.com.gadolfolozano.mymovie.ui.login.signin.SignInFragment
import pe.com.gadolfolozano.mymovie.ui.search.SearchActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator, HasSupportFragmentInjector {

    @Inject
    lateinit var mLoginViewModel: LoginViewModel

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    var mBinding: ActivityLoginBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel
        get() = mLoginViewModel

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigator = this

        mBinding = getViewDataBinding()

        setUp()
    }

    private fun setUp() {
        navigateToSignIn()
    }

    override fun navigateToSignIn() {
        val signInFragment = SignInFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, signInFragment)
            .commitAllowingStateLoss()
    }

    override fun naviageToCreateAccount() {
        val createAccountFragment = CreateAccountFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, createAccountFragment)
            .commitAllowingStateLoss()
    }

    override fun openMainActivity() {
        val intent = SearchActivity.newIntent(this@LoginActivity)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment != null && fragment is CreateAccountFragment) {
            navigateToSignIn()
            return
        }

        super.onBackPressed()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

}