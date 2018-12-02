package pe.com.gadolfolozano.mymovie.model.response

open class BaseResponseModel {

    var status: String? = null
    var errorMessage: String? = null

    companion object {
        val STATUS_SUCCESS = "success"
        val STATUS_LOADING = "loading"
        val STATUS_ERROR = "error"
    }
}
