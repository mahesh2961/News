package `in`.news.di.component

import `in`.news.NewsApp
import `in`.news.di.module.DataModule
import `in`.news.di.module.ActivityModule
import `in`.news.di.module.AppModule
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Dependency injection Builder class
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class
))
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApp>() {
        @BindsInstance abstract fun appContext(appContext: Context): Builder
    }
}