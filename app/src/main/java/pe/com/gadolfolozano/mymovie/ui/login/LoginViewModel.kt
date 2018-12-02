package pe.com.gadolfolozano.mymovie.ui.login

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

class LoginViewModel(dataManager: DataManager) : BaseViewModel<LoginNavigator>(dataManager) {
    fun doLogin(username: String, password: String): LiveData<LoginResponseModel> {
        return dataManager.doLogin(username, password)
    }
}
