package `in`.news.di.module

import `in`.news.di.InjectingViewModelFactory
import `in`.news.di.ViewModelKey
import `in`.news.ui.details.viewmodel.WebViewViewModel
import `in`.news.ui.news.viewmodel.NewsViewModel
import `in`.news.ui.news.viewmodel.NewsHostViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(NewsHostViewModel::class)
    abstract fun bindNewsHostViewModel(newsHostViewModel: NewsHostViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(WebViewViewModel::class)
    abstract fun bindWebViewViewModel(webViewViewModel: WebViewViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory
}