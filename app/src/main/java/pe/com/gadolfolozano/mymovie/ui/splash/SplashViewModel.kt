package pe.com.gadolfolozano.mymovie.ui.splash

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

class SplashViewModel(val dataManager: DataManager) : BaseViewModel<SplashNavigator>() {

    fun getCurrentUser(): LiveData<LoginResponseModel> {
        return dataManager.getCurrentUser()
    }
}
