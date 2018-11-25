package pe.com.gadolfolozano.mymovie.ui.main

import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.data.DataManager

@Module
class MainActivityModule {
    @Provides
    internal fun provideSplashViewModel(dataManager: DataManager): MainViewModel {
        return MainViewModel(dataManager)
    }
}
