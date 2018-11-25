package pe.com.gadolfolozano.mymovie.ui.login

import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.data.DataManager

@Module
class LoginModule {
    @Provides
    internal fun provideLoginViewModel(dataManager: DataManager): LoginViewModel {
        return LoginViewModel(dataManager)
    }
}