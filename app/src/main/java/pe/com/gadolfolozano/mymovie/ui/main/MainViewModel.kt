package pe.com.gadolfolozano.mymovie.ui.main

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

class MainViewModel(dataManager: DataManager) : BaseViewModel<MainNavigator>(dataManager) {

    fun logout(): LiveData<BaseResponseModel> {
        return dataManager.doLogout()
    }
}
