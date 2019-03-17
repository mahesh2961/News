package `in`.news.ui.news

import `in`.news.R
import `in`.news.databinding.FragmentNewsHostBinding
import `in`.news.ui.base.BaseFragment
import `in`.news.ui.news.adapter.NewsCategoryFragmentPagerAdapter
import `in`.news.ui.news.viewmodel.NewsHostViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

/**
 * Fragment to display View pager of categories
 */
class NewsHostFragment : BaseFragment() {


    private val vModel: NewsHostViewModel by lazy {
        ViewModelProviders.of(this, vmFactory).get(NewsHostViewModel::class.java)
    }

    private val adapter by lazy {
        NewsCategoryFragmentPagerAdapter(mutableListOf(), childFragmentManager)
    }


    override fun getTitle(): String {
        return getString(R.string.news_fragment_title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNewsHostBinding.inflate(inflater, container, false)

        binding.apply {
            setLifecycleOwner(viewLifecycleOwner)
            pager.adapter = adapter
            tabLayout.setupWithViewPager(pager, true)
        }
        setDataObserver()
        return binding.root
    }


    /**
     * Observes LiveData list of Categories provided by [vModel]
     */
    private fun setDataObserver() {
        vModel.getData().observe(this, Observer { categories ->
            adapter.data = categories
            adapter.notifyDataSetChanged()
        })
    }

}