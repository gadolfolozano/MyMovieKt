package pe.com.gadolfolozano.mymovie.ui.login.signin

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SignInFragmentProvider {
    @ContributesAndroidInjector(modules = arrayOf(SignInFragmentModule::class))
    internal abstract fun provideSignInFragmentFactory(): SignInFragment
}
