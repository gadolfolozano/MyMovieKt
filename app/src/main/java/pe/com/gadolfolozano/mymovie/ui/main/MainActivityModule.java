package pe.com.gadolfolozano.mymovie.ui.main;

import dagger.Module;
import dagger.Provides;
import pe.com.gadolfolozano.mymovie.data.DataManager;

@Module
public class MainActivityModule {
    @Provides
    MainViewModel provideSplashViewModel(DataManager dataManager) {
        return new MainViewModel(dataManager);
    }
}
