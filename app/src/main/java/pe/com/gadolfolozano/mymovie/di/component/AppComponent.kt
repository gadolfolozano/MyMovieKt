package pe.com.gadolfolozano.mymovie.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import pe.com.gadolfolozano.mymovie.MyMoviesApplication
import pe.com.gadolfolozano.mymovie.di.builder.ActivityBuilder
import pe.com.gadolfolozano.mymovie.di.module.AppModule

import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class))
interface AppComponent {

    fun inject(app: MyMoviesApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
