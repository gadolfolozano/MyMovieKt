package pe.com.gadolfolozano.mymovie.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


abstract class BaseViewModel<N> : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    var navigator: N? = null
}
