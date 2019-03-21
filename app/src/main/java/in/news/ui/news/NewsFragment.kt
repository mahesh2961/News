package `in`.news.ui.news

import `in`.news.R
import `in`.news.databinding.FragmentNewsListBinding
import `in`.news.ui.base.BaseFragment
import `in`.news.ui.model.Category
import `in`.news.ui.news.adapter.MarginItemDecoration
import `in`.news.ui.news.adapter.NewsListAdapter
import `in`.news.ui.news.viewmodel.NewsViewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

/**
 * Fragment to display list of articles in Category
 */
class NewsFragment : BaseFragment() {

    var category: Category? = null

    lateinit var binding: FragmentNewsListBinding


    private val adapter: NewsListAdapter by lazy {
        NewsListAdapter()
    }

    private val vModel: NewsViewModel by lazy {
        ViewModelProviders.of(this, vmFactory).get(NewsViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        binding.apply {
            setLifecycleOwner(viewLifecycleOwner)
            viewModel = vModel
            list.adapter = adapter
            list.addItemDecoration(MarginItemDecoration())
        }

        setDataObserver(adapter)

        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        category = arguments?.getParcelable(KEY_CATEGORY)
    }


    override fun getTitle(): String {
        return getString(R.string.news_fragment_title)
    }

    /**
     * Observes LiveData list of articles provided by [vModel]
     * @param adapter
     */
    private fun setDataObserver(adapter: NewsListAdapter) {

        vModel.getNewsForCategory(category = category?.label!!).observe(viewLifecycleOwner, Observer { categories ->
            adapter.submitList(categories)
        })
    }


    companion object {
        const val KEY_CATEGORY = "new.category"

        /**
         * provides Instance of [NewsFragment]
         * @param category
         */
        fun getInstance(category: Category): NewsFragment {

            val fragment = NewsFragment()
            val args = Bundle()
            args.putParcelable(KEY_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

}