package `in`.news.repository

import `in`.news.R
import `in`.news.data.db.AppDatabase
import `in`.news.data.db.model.Article
import `in`.news.data.db.model.Source
import `in`.news.data.network.ApiHandler
import `in`.news.data.network.model.ArticleResponse
import `in`.news.data.repository.NewsRepository
import `in`.news.di.ResourceProvider
import `in`.news.utils.DATABASE_NAME
import `in`.news.utils.STATUS_SUCCESS
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.sql.Timestamp


@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTesting
{

    @Mock
    lateinit var apiHandler: ApiHandler

    lateinit var newsRepository: NewsRepository


    @Mock
    private val provider: ResourceProvider? = null


    @Before
    internal fun setUp() {
        MockitoAnnotations.initMocks(this)
        val appContext = InstrumentationRegistry.getTargetContext()
        var dao = Room.databaseBuilder<AppDatabase>(appContext, AppDatabase::class.java!!, DATABASE_NAME).build().getNewsDao()
        Mockito.`when`(provider?.getString(R.string.message_network_error)).thenReturn("aaaaa")
        newsRepository = NewsRepository(dao, apiHandler, provider!!)

    }



    fun getTestData() : ArticleResponse
    {
        var articles:MutableList<Article> = mutableListOf()
        var article = Article(1001,"sssdsd","sdsddsd","sddsdsdsdsdsds","sdsdsdsdsdsddsds", Timestamp(System.currentTimeMillis()),"dssdsdsdsdsdd"
        ,"cccccc", Source("1111","ddddddd"),"dsdsdsd")
        articles.add(article)

        var response=ArticleResponse()
        response.status= STATUS_SUCCESS
        response.articles=articles
        return response;
    }


    @Test
    fun testSuccessFetchAndPersistNewsForCategory() {
        val TEST_CATEGORY="Test_cat"

        var response:Response<ArticleResponse> = Response.success(getTestData())
        val def= CompletableDeferred(response)
        Mockito.`when`(apiHandler.getHeadlinesForCategory(TEST_CATEGORY)).thenReturn(def)

        runBlocking {
            newsRepository.fetchAndPersistNewsForCategory(TEST_CATEGORY)
            val fetchedCategories =newsRepository.newsDao.getHeadlinesForCategoryTest(TEST_CATEGORY)
            Assert.assertFalse(fetchedCategories.isEmpty());
        }

    }

    @Test
    fun testErrorFetchAndPersistNewsForCategory() {
        val TEST_CATEGORY="Test_cat2"
        var response:Response<ArticleResponse> = Response.error(500, ResponseBody.create(MediaType.parse("application/json"),""))
        val def= CompletableDeferred(response)
        Mockito.`when`(apiHandler.getHeadlinesForCategory(TEST_CATEGORY)).thenReturn(def)

        runBlocking {
            newsRepository.fetchAndPersistNewsForCategory(TEST_CATEGORY)
            val fetchedCategories =newsRepository.newsDao.getHeadlinesForCategoryTest(TEST_CATEGORY)
            Assert.assertTrue(fetchedCategories.isEmpty());

        }

    }





}

