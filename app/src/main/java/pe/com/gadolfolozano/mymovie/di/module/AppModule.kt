package pe.com.gadolfolozano.mymovie.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.data.DataManager
import pe.com.gadolfolozano.mymovie.data.DataManagerImplements

import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideDataManager(dataManagerImplements: DataManagerImplements): DataManager {
        return dataManagerImplements
    }

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

}
