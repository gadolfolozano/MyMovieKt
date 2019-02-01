package pe.com.gadolfolozano.mymovie.data.firebase

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import pe.com.gadolfolozano.mymovie.model.UserModel
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseHelperImplements @Inject constructor() : FirebaseHelper {

    val mAuth = FirebaseAuth.getInstance()
    val mDataBase = FirebaseDatabase.getInstance().reference

    override fun getCurrentUser(): LiveData<LoginResponseModel> {
        val data = MutableLiveData<LoginResponseModel>()

        val loginResponse = LoginResponseModel()
        val userModel = UserModel()
        if (mAuth.currentUser != null) {
            userModel.id = mAuth.currentUser?.uid
            userModel.name = mAuth.currentUser?.displayName
            userModel.email = mAuth.currentUser?.email
            loginResponse.user = userModel
            loginResponse.status = BaseResponseModel.STATUS_SUCCESS
        } else {
            loginResponse.status = BaseResponseModel.STATUS_ERROR
        }
        data.value = loginResponse

        return data
    }

    override fun doLogin(username: String, password: String): LiveData<LoginResponseModel> {
        val data = MutableLiveData<LoginResponseModel>()

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener { task: Task<AuthResult> ->
            data.value = handleLoginResponse(task)
        }

        val prepareResponse = LoginResponseModel()
        prepareResponse.status = BaseResponseModel.STATUS_LOADING
        data.value = prepareResponse

        return data
    }

    override fun createAccount(username: String, password: String): LiveData<LoginResponseModel> {
        val data = MutableLiveData<LoginResponseModel>()

        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener { task: Task<AuthResult> ->
            data.value = handleLoginResponse(task)
        }

        val prepareResponse = LoginResponseModel()
        prepareResponse.status = BaseResponseModel.STATUS_LOADING
        data.value = prepareResponse

        return data
    }

    override fun doLogout(): LiveData<BaseResponseModel> {
        val data = MutableLiveData<BaseResponseModel>()

        val response = BaseResponseModel()
        mAuth.signOut()
        response.status = BaseResponseModel.STATUS_SUCCESS
        data.value = response

        return data
    }

    private fun handleLoginResponse(task: Task<AuthResult>): LoginResponseModel {
        val loginResponse = LoginResponseModel()
        if (task.isSuccessful) {
            val userModel = UserModel()
            userModel.id = mAuth.currentUser?.uid
            userModel.name = mAuth.currentUser?.displayName
            userModel.email = mAuth.currentUser?.email
            mDataBase.child("myMovieUsers").child(userModel.id!!).setValue(userModel)
            loginResponse.user = userModel
            loginResponse.status = BaseResponseModel.STATUS_SUCCESS
        } else {
            loginResponse.errorMessage = task.exception?.message
            loginResponse.status = BaseResponseModel.STATUS_ERROR
        }

        return loginResponse
    }
}