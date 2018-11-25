package pe.com.gadolfolozano.mymovie.ui.splash

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.BaseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

class SplashViewModel(dataManager: DataManager) : BaseViewModel<SplashNavigator>(dataManager) {

    val dummyValue: LiveData<BaseModel>
        get() = dataManager.dummyValue
}
