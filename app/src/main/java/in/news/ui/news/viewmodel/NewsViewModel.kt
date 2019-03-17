package `in`.news.ui.news.viewmodel

import `in`.news.data.db.model.Article
import `in`.news.data.network.model.base.BaseResult
import `in`.news.data.repository.NewsRepository
import `in`.news.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for [NewsFragment]
 */
class NewsViewModel @Inject constructor(
        private val newsRepository: NewsRepository
) : BaseViewModel(), SwipeRefreshLayout.OnRefreshListener {


    val viewModelJob = Job()

    var viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob) // Co-routine scope IO thread

    private lateinit var category: String

    lateinit var liveNewsList: LiveData<List<Article>>


    /**
     * Sets LiveData list of articles
     * Updates data when changes are made to database
     * @param category
     */
    private fun getNews(category: String) {
        liveNewsList = newsRepository.getNewsForCategory(category)
    }

    /**
     * @return current Livedata  list of articles
     */
    private fun getObservableData() = liveNewsList

    fun getNewsForCategory(category: String): LiveData<List<Article>> {
        init(category)
        fetchCategoriesIfNotAvailable(category)
        return getObservableData()
    }

    private fun init(category: String)
    {
        this.category = category
        getNews(category)
    }

    /**
     * Fetches articles for [category] via api using [newsRepository]
     * @param category
     */
    fun fetchNewsForCategory(category: String) {

        //launching a coroutine on worker thread
        viewModelScope.launch {
            displayLoader()
            val response = newsRepository.fetchAndPersistNewsForCategory(category)
            when (response) {
                is BaseResult.Success -> hideLoader()
                is BaseResult.Error -> showErrorMessage(response.exception.message)
            }
        }

    }

    /**
     * Fetches articles for [category] via api using [newsRepository] if not available in offline DB
     * @param category
     */
    private fun fetchCategoriesIfNotAvailable(category: String) {
        viewModelScope.launch {
            if (!newsRepository.isDataAvailableOffline(category)) fetchNewsForCategory(category)
        }
    }


    /**
     * Re-fetches articles for [category] form api
     * This functioned is accessed via data binding
     */
    override fun onRefresh() {
        fetchNewsForCategory(category)
    }


    /**
     * Show error Message on Screen
     */
    private fun showErrorMessage(message: String?) {
        hideLoader()
        postDisplayMessage(message)
    }

    /**
     * display loader progress view on screen
     */
    private fun displayLoader() {
        displayLoader(true)
        hideErrorMessage()
    }

    /**
     * hide loader progress view on screen
     */
    private fun hideLoader() {
        displayLoader(false)

    }

    /**
     * Cancels coroutine lunched by ViewModel
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}