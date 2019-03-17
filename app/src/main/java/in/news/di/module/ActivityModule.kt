package `in`.news.di.module

import `in`.news.ui.MainActivity
import `in`.news.ui.details.WebViewFragment
import `in`.news.ui.news.NewsHostFragment
import `in`.news.ui.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment


    @ContributesAndroidInjector
    abstract fun contributeNewsHostFragment(): NewsHostFragment

    @ContributesAndroidInjector
    abstract fun contributeWebViewFragment(): WebViewFragment

}