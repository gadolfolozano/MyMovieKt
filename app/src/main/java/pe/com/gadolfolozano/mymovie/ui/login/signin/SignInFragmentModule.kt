package pe.com.gadolfolozano.mymovie.ui.login.signin

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.ViewModelProviderFactory
import pe.com.gadolfolozano.mymovie.data.DataManager

@Module
class SignInFragmentModule {
    @Provides
    internal fun signInViewModel(dataManager: DataManager): SignInViewModel {
        return SignInViewModel(dataManager)
    }

    @Provides
    internal fun providesignInViewModel(signInViewModel: SignInViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(signInViewModel)
    }
}