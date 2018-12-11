package pe.com.gadolfolozano.mymovie.data.firebase

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel

interface FirebaseHelper {
    fun doLogin(username: String, password: String): LiveData<LoginResponseModel>

    fun createAccount(username: String, password: String): LiveData<LoginResponseModel>
}