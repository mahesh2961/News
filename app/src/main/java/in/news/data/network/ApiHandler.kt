package `in`.news.data.network

import `in`.news.data.network.model.ArticleResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api Handler for Retrofit
 */
interface ApiHandler {

    /**
     * Fetches List of articles for given category from api
     * @param category
     * @return deferred instance of ArticleResponse containing list of articles
     */
    @GET("top-headlines?apiKey=7019973f03494525b62199f2e92fe71f&country=us")
    fun getHeadlinesForCategory(@Query("category") category:String): Deferred<Response<ArticleResponse>>
}