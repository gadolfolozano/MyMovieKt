package pe.com.gadolfolozano.mymovie.ui.login.signin

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

class SignInViewModel(val dataManager: DataManager) : BaseViewModel<SignInNavigator>() {
    fun doLogin(username: String, password: String): LiveData<LoginResponseModel> {
        return dataManager.doLogin(username, password)
    }
}