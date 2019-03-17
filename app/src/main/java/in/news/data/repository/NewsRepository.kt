package `in`.news.data.repository

import `in`.news.R
import `in`.news.data.db.dao.NewsDao
import `in`.news.data.db.model.Article
import `in`.news.data.network.ApiHandler
import `in`.news.data.network.model.ArticleResponse
import `in`.news.data.network.model.base.BaseResult
import `in`.news.di.ResourceProvider
import `in`.news.utils.Util
import androidx.annotation.WorkerThread
import javax.inject.Inject

/**
 * Repository to provide News from Database or web Service
 */

open class NewsRepository @Inject constructor(var newsDao : NewsDao, var networkLayer : ApiHandler,resourceProvider: ResourceProvider) :BaseRepository(resourceProvider) {


    /**
     * This functions returns LiveData List of articles for given [category]
     * @param category
     * @Returns LiveData<List<Articles>>
     */
    fun getNewsForCategory(category: String) = newsDao.getHeadlinesForCategory(category)

    /**
     * Performs bulk insertion of [articles] into database
     * @param articles
     */
    @WorkerThread
    fun bulkInsertNews(articles: MutableList<Article>) {
        newsDao.insertAll(articles)
    }

    /**
     * Perform fetching of news articles for given [category] from api
     * and save the same in database
     * @param category
     * @return ArticleResponse in success/error format
     */
    @WorkerThread
    suspend fun fetchAndPersistNewsForCategory(category: String) : BaseResult<ArticleResponse>
    {
        val newsResponse=fetchNewsForCategory(category)
        persistDataIfSuccessResponse(response = newsResponse,category = category)
        return newsResponse
    }


    /**
     * Performs insertion of news articles into DB if [response] is success
     * Sets category field for each article using [category] in params
     * Sets elapsed time field for each article
     * @param response
     * @param category
     */
    private fun persistDataIfSuccessResponse(response:BaseResult<ArticleResponse>,category: String)
    {
        when (response) {
            is BaseResult.Success -> {
                response.data.articles.forEach {
                    it.category = category
                    //This is based on assumption that source name will be in format "NAME.COM"
                    it.elapsedTime = Util.getSourceAndElapsedTime(it.source.name.split(".")[0],it.publishedAt)
                }
                //Delete previous records for category as we do not have unique identifier for articles via Api
                newsDao.deleteNewsForCategory(category)
                bulkInsertNews(response.data.articles)
            }
        }
    }



    /**
     * Perform fetching of news articles for given [category] from api
     * @param category
     * @return ArticleResponse in success/error format
     */
    private suspend fun fetchNewsForCategory(category: String) : BaseResult<ArticleResponse>
    {
        val result = safeApiCall(
                call = {networkLayer.getHeadlinesForCategory(category).await()},
                networkErrorMessage = resourceProvider.getString(R.string.message_network_error)
        )
        return result
    }

    /**
     * Checks if news articles are available for given [category] in db
     * @param category
     * @return Boolean
     */
    fun isDataAvailableOffline(category: String)=(newsDao.getNumberOfRows(category)>0)


}