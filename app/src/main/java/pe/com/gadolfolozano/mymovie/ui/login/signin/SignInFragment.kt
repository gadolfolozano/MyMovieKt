package pe.com.gadolfolozano.mymovie.ui.login.signin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import pe.com.gadolfolozano.mymovie.BR
import pe.com.gadolfolozano.mymovie.R
import pe.com.gadolfolozano.mymovie.databinding.FragmentSignInBinding
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseFragment
import pe.com.gadolfolozano.mymovie.ui.util.StringUtil

import javax.inject.Inject

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>(), SignInNavigator {

    @Inject
    lateinit var mSignInViewModel: SignInViewModel

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var mBinding: FragmentSignInBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_sign_in
    override val viewModel: SignInViewModel
        get() = ViewModelProviders.of(this, mViewModelFactory).get(SignInViewModel::class.java);


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = getViewDataBinding()
        setUp()
    }

    fun setUp() {
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

        mSignInViewModel.doLogin(email, password).observe(this,
            Observer<LoginResponseModel> { loginResponseModel ->
                when (loginResponseModel?.status) {
                    BaseResponseModel.STATUS_LOADING -> {
                        showLoading()
                    }
                    BaseResponseModel.STATUS_ERROR -> {
                        hideLoading()
                        Toast.makeText(activity, loginResponseModel.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                    BaseResponseModel.STATUS_SUCCESS -> {
                        hideLoading()
                        Toast.makeText(
                            activity,
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

    override fun navigateToCreateAccount() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigatetoMain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(): SignInFragment {
            val args = Bundle()
            val fragment = SignInFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
