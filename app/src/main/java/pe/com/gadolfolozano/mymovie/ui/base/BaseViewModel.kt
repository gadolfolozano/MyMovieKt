package pe.com.gadolfolozano.mymovie.ui.base

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel<N> : ViewModel() {
    var navigator: N? = null
}
