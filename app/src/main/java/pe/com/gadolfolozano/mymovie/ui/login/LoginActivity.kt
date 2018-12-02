package pe.com.gadolfolozano.mymovie.ui.login

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.ActivityLoginBinding
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseActivity
import pe.com.gadolfolozano.mymovie.ui.main.MainActivity
import pe.com.gadolfolozano.mymovie.ui.util.StringUtil
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    @Inject
    lateinit var mLoginViewModel: LoginViewModel

    var mBinding: ActivityLoginBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel
        get() = mLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigator = this

        mBinding = getViewDataBinding()

        mBinding?.tvUsername?.addTextChangedListener(LoginTextWatcher())
        mBinding?.tvPassword?.addTextChangedListener(LoginTextWatcher())
        mBinding?.tvPassword?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && validateInputs()) {
                mBinding?.buttonLogin?.performClick()
            }
            false
        }

        mBinding?.buttonLogin?.isEnabled = false
        mBinding?.buttonLogin?.setOnClickListener {
            onButtonLoginClicked()
        }
    }

    private fun onButtonLoginClicked() {
        hideKeyboard()
        showLoading()
        val email = mBinding?.tvUsername?.text.toString()
        val password = mBinding?.tvPassword?.text.toString()

        mLoginViewModel.doLogin(email, password).observe(this,
            Observer<LoginResponseModel> { loginResponseModel ->
                when (loginResponseModel?.status) {
                    BaseResponseModel.STATUS_LOADING -> {
                        showLoading()
                    }
                    BaseResponseModel.STATUS_ERROR -> {
                        hideLoading()
                        Toast.makeText(this@LoginActivity, loginResponseModel.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                    BaseResponseModel.STATUS_SUCCESS -> {
                        hideLoading()
                        Toast.makeText(
                            this@LoginActivity,
                            "user id: " + loginResponseModel.user?.id,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            })
    }

    private fun validateInputs(): Boolean {
        val passwordLength = mBinding?.tvPassword?.text?.length ?: 0
        return StringUtil.validateEmail(mBinding?.tvUsername?.text.toString()) && passwordLength >= 6
    }

    internal inner class LoginTextWatcher : TextWatcher {

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            //Do nothing
        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            //Do nothing
        }

        override fun afterTextChanged(editable: Editable) {
            checkEnableButton()
        }
    }

    private fun checkEnableButton() {
        mBinding?.buttonLogin?.isEnabled = validateInputs()
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this@LoginActivity)
        startActivity(intent)
        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

}