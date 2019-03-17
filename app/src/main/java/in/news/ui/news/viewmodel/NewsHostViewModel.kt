package `in`.news.ui.news.viewmodel

import `in`.news.R
import `in`.news.di.ResourceProvider
import `in`.news.ui.base.BaseViewModel
import `in`.news.ui.model.Category
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class NewsHostViewModel @Inject constructor(
        private val resourceProvider: ResourceProvider
): BaseViewModel()
{
    var categoryList =MutableLiveData<MutableList<Category>>()


    init {
        setData()
    }

    /**
     * Returns a list of categories for news
     */
    private fun getCategories() : MutableList<Category>
   {
       var categories:MutableList<Category> = mutableListOf()
       resourceProvider.getStringArray(R.array.categories).forEach {
           categories.add(Category(it.toLowerCase()))
       }

       return categories;
   }

    private fun setData() =categoryList.postValue(getCategories())


    fun getData() = categoryList

}