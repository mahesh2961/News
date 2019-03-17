package `in`.news.data.db

import `in`.news.data.db.dao.NewsDao
import `in`.news.data.db.model.Article
import `in`.news.data.db.utils.Converter
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dagger.Module

/**
 * Setting Rooms Database
 */
@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
@Module
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}