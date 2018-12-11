package pe.com.gadolfolozano.mymovie.ui.login.createaccount

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

class CreateAccountViewModel(dataManager: DataManager) : BaseViewModel<CreateAccountNavigator>(dataManager) {
    fun createAccount(username: String, password: String): LiveData<LoginResponseModel> {
        return dataManager.createAccount(username, password)
    }
}