package `in`.news.data.db.dao

import `in`.news.data.db.model.Article
import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * DAO TO handle data access
 */
@Dao
interface NewsDao {

    /**
     * Get all rows for given category
     * @param category
     */
    @Query("SELECT * FROM Article where category=:category")
    fun getHeadlinesForCategory(category: String) : LiveData<List<Article>>


    /**
     * Bulk insert list of articles
     * @param List<Article>
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)  //Replace in case of duplication
    fun insertAll(articiles: List<Article>)


    /**
     * Provides count of rows for a given category
     * @param category
     * @return int
     */
    @Query("SELECT COUNT(title) FROM Article where category=:category")
    fun getNumberOfRows(category: String): Int

    /**
     * Deletes all records for a given category
     * @param category
     */
    @Query("DELETE FROM Article Where category=:category")
    fun deleteNewsForCategory(category: String)


    /**
     * Get all rows for given category
     * This method is used for Unit Testing
     * @param category
     */
    @Query("SELECT * FROM Article where category=:category")
    fun getHeadlinesForCategoryTest(category: String) : List<Article>

}