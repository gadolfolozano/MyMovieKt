package pe.com.gadolfolozano.mymovie.data

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.firebase.FirebaseHelper
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImplements @Inject constructor(val firebaseHelper: FirebaseHelper) : DataManager {

    override fun doLogin(username: String, password: String): LiveData<LoginResponseModel> {
        return firebaseHelper.doLogin(username, password)
    }

    override fun createAccount(username: String, password: String): LiveData<LoginResponseModel> {
        return firebaseHelper.createAccount(username, password)
    }

    override fun getCurrentUser(): LiveData<LoginResponseModel> {
        return firebaseHelper.getCurrentUser()
    }

    override fun doLogout(): LiveData<BaseResponseModel> {
        return firebaseHelper.doLogout()
    }
}
