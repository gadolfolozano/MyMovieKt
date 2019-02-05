package pe.com.gadolfolozano.mymovie.ui.search

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.ui.base.BaseViewModel

class SearchViewModel(dataManager: DataManager) : BaseViewModel<SearchNavigator>(dataManager) {

    fun logout(): LiveData<BaseResponseModel> {
        return dataManager.doLogout()
    }
}