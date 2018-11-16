package pe.com.gadolfolozano.mymovie.model

class BaseModel {

    var status: String? = null
    var isLoading: Boolean = false
    var errorMessage: String? = null

    companion object {
        val STATUS_SUCCESS = "success"
        val STATUS_ERROR = "error"
    }
}
