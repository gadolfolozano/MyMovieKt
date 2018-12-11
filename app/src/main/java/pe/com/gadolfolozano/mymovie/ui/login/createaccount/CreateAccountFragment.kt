package pe.com.gadolfolozano.mymovie.ui.login.createaccount

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
import pe.com.gadolfolozano.mymovie.databinding.FragmentCreateAccountBinding
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseFragment
import pe.com.gadolfolozano.mymovie.ui.login.LoginActivity
import pe.com.gadolfolozano.mymovie.ui.util.Constants
import pe.com.gadolfolozano.mymovie.ui.util.StringUtil
import javax.inject.Inject

class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding, CreateAccountViewModel>(),
    CreateAccountNavigator {

    @Inject
    lateinit var mCreateAccountViewModel: CreateAccountViewModel

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var mBinding: FragmentCreateAccountBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_create_account
    override val viewModel: CreateAccountViewModel
        get() = ViewModelProviders.of(this, mViewModelFactory).get(CreateAccountViewModel::class.java);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = getViewDataBinding()

        setup()
    }

    fun setup() {
        mBinding?.tvUsername?.addTextChangedListener(CreateAccountTextWatcher())
        mBinding?.tvPassword?.addTextChangedListener(CreateAccountTextWatcher())
        mBinding?.tvRePassword?.addTextChangedListener(CreateAccountTextWatcher())
        mBinding?.tvRePassword?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && validateInputs()) {
                mBinding?.buttonCreateAccount?.performClick()
            }
            false
        }

        mBinding?.buttonCreateAccount?.isEnabled = false
        mBinding?.buttonCreateAccount?.setOnClickListener {
            onButtonCreateAccountClicked()
        }
    }

    private fun onButtonCreateAccountClicked() {
        hideKeyboard()
        showLoading()
        val email = mBinding?.tvUsername?.text.toString()
        val password = mBinding?.tvPassword?.text.toString()

        mCreateAccountViewModel.createAccount(email, password).observe(this,
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
                        navigatetoMain()
                    }
                }
            })
    }

    override fun navigatetoMain() {
        if (activity is LoginActivity) {
            (activity as LoginActivity).openMainActivity()
        }
    }

    internal inner class CreateAccountTextWatcher : TextWatcher {

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
        mBinding?.buttonCreateAccount?.isEnabled = validateInputs()
    }

    private fun validateInputs(): Boolean {
        val passwordLength = mBinding?.tvPassword?.text?.length ?: 0
        val passwordMatch = mBinding?.tvPassword?.text?.toString()
            ?.equals(mBinding?.tvRePassword?.text?.toString() ?: Constants.EMPTY_STRING) ?: false
        return passwordMatch && StringUtil.validateEmail(mBinding?.tvUsername?.text.toString()) && passwordLength >= 6
    }

    companion object {
        fun newInstance(): CreateAccountFragment {
            val args = Bundle()
            val fragment = CreateAccountFragment()
            fragment.arguments = args
            return fragment
        }
    }
}