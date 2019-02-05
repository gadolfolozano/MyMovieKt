package pe.com.gadolfolozano.mymovie.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.com.gadolfolozano.mymovie.ui.login.LoginActivity
import pe.com.gadolfolozano.mymovie.ui.login.LoginModule
import pe.com.gadolfolozano.mymovie.ui.login.createaccount.CreateAccountFragmentProvider
import pe.com.gadolfolozano.mymovie.ui.login.signin.SignInFragmentProvider
import pe.com.gadolfolozano.mymovie.ui.main.MainActivity
import pe.com.gadolfolozano.mymovie.ui.main.MainActivityModule
import pe.com.gadolfolozano.mymovie.ui.search.SearchActivity
import pe.com.gadolfolozano.mymovie.ui.search.SearchActivityModule
import pe.com.gadolfolozano.mymovie.ui.splash.SplashActivity
import pe.com.gadolfolozano.mymovie.ui.splash.SplashActivityModule

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(
        modules = arrayOf(
            LoginModule::class,
            SignInFragmentProvider::class,
            CreateAccountFragmentProvider::class
        )
    )
    internal abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = arrayOf(SearchActivityModule::class))
    internal abstract fun bindSearchActivity(): SearchActivity
}
