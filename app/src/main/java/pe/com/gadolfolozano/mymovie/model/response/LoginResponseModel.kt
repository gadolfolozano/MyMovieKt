package pe.com.gadolfolozano.mymovie.model.response

import pe.com.gadolfolozano.mymovie.model.UserModel


class LoginResponseModel : BaseResponseModel() {
    var user: UserModel? = null
}