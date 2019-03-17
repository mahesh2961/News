package `in`.news.ui.details


import `in`.news.R
import `in`.news.databinding.WebviewFragmentBinding
import `in`.news.ui.base.BaseFragment
import `in`.news.ui.details.viewmodel.WebViewViewModel
import `in`.news.ui.utils.IActivityEventHandler
import `in`.news.ui.utils.IBackPressListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController


/**
 * Fragment to Display web site in Webview
 */
class WebViewFragment : BaseFragment(),IBackPressListener {

     val vModel: WebViewViewModel by lazy {
         ViewModelProviders.of(this, vmFactory).get(WebViewViewModel::class.java)
    }


    lateinit var binding:WebviewFragmentBinding


    var handler :IActivityEventHandler? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val url = WebViewFragmentArgs.fromBundle(arguments!!).url
        vModel.setUrl(url)
         binding = DataBindingUtil.inflate<WebviewFragmentBinding>(
                inflater, R.layout.webview_fragment, container, false).apply {
            viewModel = vModel
            setLifecycleOwner(this@WebViewFragment)
        }
        setHasOptionsMenu(true)

        return binding.root
    }




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                findNavController().navigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            handler = context as IActivityEventHandler  //We need activity reference to handle back press event for this fragment
            handler?.setOnBackPressListener(this)
        }
        showBackButton(true)
    }

    /**
     * Displays/Hides back button on ActionBar
     * @param
     */
    private fun showBackButton(show: Boolean){
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler?.setOnBackPressListener(this) //reset the back press listener to null
        showBackButton(false)

    }


    override fun getTitle(): String {
        return getString(R.string.news_fragment_title)
    }

    /**
     * check if web view can traverse back to first page in webview
     * pressing back on first page will cause navigation to previous fragment
     */
    override fun handleBackPress(): Boolean {
        var canGoBack=binding.newsWebView.canGoBack()
        if (canGoBack==true) binding.newsWebView.goBack()
        return canGoBack
    }



}

