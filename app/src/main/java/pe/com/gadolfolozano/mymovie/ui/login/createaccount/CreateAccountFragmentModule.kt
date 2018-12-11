package pe.com.gadolfolozano.mymovie.ui.login.createaccount

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.ViewModelProviderFactory
import pe.com.gadolfolozano.mymovie.data.DataManager

@Module
class CreateAccountFragmentModule {
    @Provides
    internal fun createAccountViewModel(dataManager: DataManager): CreateAccountViewModel {
        return CreateAccountViewModel(dataManager)
    }

    @Provides
    internal fun provideCreateAccountViewModel(signInViewModel: CreateAccountViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(signInViewModel)
    }
}