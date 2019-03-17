package `in`.news.ui.news.adapter

import `in`.news.ui.model.Category
import `in`.news.ui.news.NewsFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Fragment Pager Adpater to display News Categories
 */
class NewsCategoryFragmentPagerAdapter(var data:MutableList<Category>,  fragmentManager:FragmentManager) : FragmentPagerAdapter(fragmentManager)
{
    override fun getItem(position: Int): Fragment {
        return NewsFragment.getInstance(data.get(position))
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data.get(position).label
    }

}