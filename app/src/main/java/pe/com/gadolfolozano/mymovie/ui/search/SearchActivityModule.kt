package pe.com.gadolfolozano.mymovie.ui.search

import dagger.Module
import dagger.Provides
import pe.com.gadolfolozano.mymovie.data.DataManager

@Module
class SearchActivityModule {
    @Provides
    internal fun provideSearchViewModel(dataManager: DataManager): SearchViewModel {
        return SearchViewModel(dataManager)
    }
}
