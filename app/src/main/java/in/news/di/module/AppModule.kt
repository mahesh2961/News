package `in`.news.di.module

import `in`.news.data.db.AppDatabase
import `in`.news.di.ResourceProvider
import android.content.Context
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class, NetworkModule::class,AppDatabase::class,DataModule::class])
class AppModule {

    @Provides
    fun providesResources(context: Context): ResourceProvider = AndroidResourceProvider(context)

}