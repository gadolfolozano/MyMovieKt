package pe.com.gadolfolozano.mymovie.data.firebase

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import pe.com.gadolfolozano.mymovie.model.UserModel
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseHelperImplements @Inject constructor() : FirebaseHelper {

    override fun doLogin(username: String, password: String): LiveData<LoginResponseModel> {
        val data = MutableLiveData<LoginResponseModel>()
        val mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task: Task<AuthResult> ->
            val loginResponse = LoginResponseModel()
            if (task.isSuccessful) {
                val userModel = UserModel()
                userModel.id = mAuth.currentUser?.uid
                loginResponse.user = userModel
                loginResponse.status = BaseResponseModel.STATUS_SUCCESS
            } else {
                loginResponse.errorMessage = task.exception?.message
                loginResponse.status = BaseResponseModel.STATUS_ERROR
            }

            data.value = loginResponse
        }

        val prepareResponse = LoginResponseModel()
        prepareResponse.status = BaseResponseModel.STATUS_LOADING
        data.value = prepareResponse

        return data
    }

    override fun createAccount(username: String, password: String): LiveData<LoginResponseModel> {
        val data = MutableLiveData<LoginResponseModel>()
        val mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener { task: Task<AuthResult> ->
            val loginResponse = LoginResponseModel()
            if (task.isSuccessful) {
                val userModel = UserModel()
                userModel.id = mAuth.currentUser?.uid
                loginResponse.user = userModel
                loginResponse.status = BaseResponseModel.STATUS_SUCCESS
            } else {
                loginResponse.errorMessage = task.exception?.message
                loginResponse.status = BaseResponseModel.STATUS_ERROR
            }

            data.value = loginResponse
        }

        val prepareResponse = LoginResponseModel()
        prepareResponse.status = BaseResponseModel.STATUS_LOADING
        data.value = prepareResponse

        return data
    }
}