package pe.com.gadolfolozano.mymovie.ui.splash

import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.data.DataManager

@Module
class SplashActivityModule {
    @Provides
    internal fun provideSplashViewModel(dataManager: DataManager): SplashViewModel {
        return SplashViewModel(dataManager)
    }
}