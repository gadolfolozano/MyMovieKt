package pe.com.gadolfolozano.mymovie.data

import android.arch.lifecycle.LiveData
import pe.com.gadolfolozano.mymovie.data.firebase.FirebaseHelper
import pe.com.gadolfolozano.mymovie.data.remote.ApiHelperImplements
import pe.com.gadolfolozano.mymovie.data.wrapper.StateData
import pe.com.gadolfolozano.mymovie.model.MovieWrapperModel
import pe.com.gadolfolozano.mymovie.model.SearchMoviesModel
import pe.com.gadolfolozano.mymovie.model.response.BaseResponseModel
import pe.com.gadolfolozano.mymovie.model.response.LoginResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImplements @Inject constructor(
    val apiHelper: ApiHelperImplements,
    val firebaseHelper: FirebaseHelper
) : DataManager {

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

    override fun obtainMovie(movieName: String?): LiveData<StateData<MovieWrapperModel>>? {
        return apiHelper.obtainMovie(movieName)
    }

    override fun obtainMovies(movies: MutableList<String>?): LiveData<StateData<SearchMoviesModel>> {
        return apiHelper.obtainMovies(movies)
    }
}
