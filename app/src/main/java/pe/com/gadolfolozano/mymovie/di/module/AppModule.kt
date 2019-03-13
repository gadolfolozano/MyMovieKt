package pe.com.gadolfolozano.mymovie.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.data.DataManagerImplements
import pe.com.gadolfolozano.mymovie.data.firebase.FirebaseHelper
import pe.com.gadolfolozano.mymovie.data.firebase.FirebaseHelperImplements
import pe.com.gadolfolozano.mymovie.data.remote.ApiHelper
import pe.com.gadolfolozano.mymovie.data.remote.ApiHelperImplements
import pe.com.gadolfolozano.mymovie.data.remote.util.ApiClient
import pe.com.gadolfolozano.mymovie.data.remote.util.ApiInterface

import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideApiHelper(apiHelperImplements: ApiHelperImplements): ApiHelper {
        return apiHelperImplements
    }

    @Provides
    @Singleton
    internal fun provideDataManager(dataManagerImplements: DataManagerImplements): DataManager {
        return dataManagerImplements
    }

    @Provides
    @Singleton
    internal fun provideFirebaseHelper(firebaseHelperImplements: FirebaseHelperImplements): FirebaseHelper {
        return firebaseHelperImplements
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideApiInterface(): ApiInterface {
        return ApiClient.getClient().create(ApiInterface::class.java)
    }

}
