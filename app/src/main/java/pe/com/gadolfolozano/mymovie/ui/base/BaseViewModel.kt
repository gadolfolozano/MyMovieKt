package pe.com.gadolfolozano.mymovie.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import pe.com.gadolfolozano.mymovie.data.DataManager

abstract class BaseViewModel<N>(val dataManager: DataManager) : ViewModel() {
    val isLoading = ObservableBoolean(false)
    var navigator: N? = null

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

}
