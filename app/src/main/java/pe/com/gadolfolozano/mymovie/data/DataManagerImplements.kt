package pe.com.gadolfolozano.mymovie.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
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

    override val dummyValue: LiveData<BaseResponseModel>
        get() {
            val data = MutableLiveData<BaseResponseModel>()
            data.value = sucess
            return data
        }

    private val sucess: BaseResponseModel
        get() {
            val baseModel = BaseResponseModel()
            baseModel.status = BaseResponseModel.STATUS_SUCCESS

            return baseModel
        }
}
