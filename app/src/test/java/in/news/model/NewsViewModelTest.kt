package `in`.news.model

import `in`.news.data.db.model.Article
import `in`.news.data.db.model.Source
import `in`.news.data.network.exception.AuthenticationException
import `in`.news.data.network.model.ArticleResponse
import `in`.news.data.network.model.base.BaseResult
import `in`.news.data.repository.NewsRepository
import `in`.news.ui.news.viewmodel.NewsViewModel
import `in`.news.utils.STATUS_SUCCESS
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.sql.Timestamp


@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Mock
    lateinit var newsRepository: NewsRepository

    lateinit var viewModel: NewsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = NewsViewModel(newsRepository)
        viewModel.viewModelScope = CoroutineScope(Dispatchers.Unconfined + viewModel.viewModelJob)


    }

    @Test
    fun testFetchNewsForCategorySuccess() {

        runBlocking {
            Mockito.`when`(newsRepository.fetchAndPersistNewsForCategory("test"))
                    .thenReturn(BaseResult.Success(getTestData()))

            viewModel.fetchNewsForCategory("test")
            Assert.assertFalse(viewModel.displayLoader.get()!!)
        }

    }

    @Test
    fun testFetchNewsForCategoryError() {

        runBlocking {
            Mockito.`when`(newsRepository.fetchAndPersistNewsForCategory("test"))
                    .thenReturn(BaseResult.Error(AuthenticationException("Authentication Exception")))

            viewModel.fetchNewsForCategory("test")
            Assert.assertEquals(viewModel.message.get()!!, "Authentication Exception")
        }

    }

    @Test
    fun testGetNewsForCategoryOffline() {
        runBlocking {
            Mockito.`when`(newsRepository.getNewsForCategory("test"))
                    .thenReturn(getLiveData())

            Mockito.`when`(newsRepository.isDataAvailableOffline("test"))
                    .thenReturn(true)

            viewModel.getNewsForCategory("test")

            Assert.assertTrue(viewModel.liveNewsList.value?.size == 1)
        }
    }


    fun getTestData(): ArticleResponse {
        var response = ArticleResponse()
        response.status = STATUS_SUCCESS
        response.articles = getListOfArticles().toMutableList()
        return response;
    }


    fun getLiveData(): LiveData<List<Article>> {

        val liveData = MutableLiveData<List<Article>>()
        liveData.value = getListOfArticles()
        return liveData
    }

    fun getListOfArticles(): List<Article> {
        var article = Article(1001, "sssdsd", "sdsddsd", "sddsdsdsdsdsds", "sdsdsdsdsdsddsds", Timestamp(System.currentTimeMillis()), "dssdsdsdsdsdd"
                , "cccccc", Source("1111", "ddddddd"), "dsdsdsd")
        var articles: List<Article> = listOf(article)
        return articles
    }
}