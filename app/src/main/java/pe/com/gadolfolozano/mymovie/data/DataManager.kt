package pe.com.gadolfolozano.mymovie.data

import pe.com.gadolfolozano.mymovie.data.firebase.FirebaseHelper
import pe.com.gadolfolozano.mymovie.data.remote.ApiHelper

interface DataManager : FirebaseHelper, ApiHelper {
    /*fun getCurrentUser(): LiveData<LoginResponseModel>

    fun doLogin(username: String, password: String): LiveData<LoginResponseModel>

    fun createAccount(username: String, password: String): LiveData<LoginResponseModel>

    fun doLogout(): LiveData<BaseResponseModel>*/
}
