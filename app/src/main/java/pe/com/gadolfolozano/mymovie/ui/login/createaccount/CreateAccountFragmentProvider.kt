package pe.com.gadolfolozano.mymovie.ui.login.createaccount

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateAccountFragmentProvider {
    @ContributesAndroidInjector(modules = arrayOf(CreateAccountFragmentModule::class))
    internal abstract fun provideCreateAccountFragmentFactory(): CreateAccountFragment
}
