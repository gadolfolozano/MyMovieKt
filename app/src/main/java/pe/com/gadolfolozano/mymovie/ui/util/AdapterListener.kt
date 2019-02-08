package pe.com.gadolfolozano.mymovie.ui.util

interface AdapterListener<T> {
    fun onItemRemoved(dataSet: List<T>, item: T)

    fun onItemAdded(dataSet: List<T>, item: T)
}
