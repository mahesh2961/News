package `in`.news.di.module

import `in`.news.data.db.AppDatabase
import `in`.news.data.db.dao.NewsDao
import `in`.news.data.network.ApiHandler
import `in`.news.data.repository.NewsRepository
import `in`.news.di.ResourceProvider
import dagger.Provides
import javax.inject.Singleton
import androidx.room.Room
import `in`.news.utils.DATABASE_NAME
import android.content.Context
import dagger.Module

/**
 * Provides Objects related to Data storage/manipulation
 */
@Module
class DataModule
{

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun providesBreadDao(database: AppDatabase): NewsDao {
        return database.getNewsDao()
    }

    @Provides
    @Singleton
    fun provideRepo(newsDao: NewsDao, networkLayer: ApiHandler,resourceProvider:ResourceProvider) : NewsRepository = NewsRepository(newsDao, networkLayer,resourceProvider)
}