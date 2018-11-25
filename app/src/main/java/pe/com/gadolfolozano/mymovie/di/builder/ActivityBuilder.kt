package pe.com.gadolfolozano.mymovie.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.com.gadolfolozano.mymovie.ui.main.MainActivity
import pe.com.gadolfolozano.mymovie.ui.main.MainActivityModule
import pe.com.gadolfolozano.mymovie.ui.splash.SplashActivity
import pe.com.gadolfolozano.mymovie.ui.splash.SplashActivityModule

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    internal abstract fun bindMainActivity(): MainActivity
}
