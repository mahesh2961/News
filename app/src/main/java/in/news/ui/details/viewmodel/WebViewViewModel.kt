package `in`.news.ui.details.viewmodel

import `in`.news.R
import `in`.news.di.ResourceProvider
import `in`.news.ui.base.BaseViewModel
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.ObservableField
import javax.inject.Inject

/**
 * View Model for [WebViewViewFrament]
 */
class WebViewViewModel @Inject constructor(var resourceProvider: ResourceProvider) : BaseViewModel() {


    var url = ObservableField<String>()

    /**
     * Sets Url for Webview
     */
    fun setUrl(url: String) = this.url.set(url)

    inner class Client : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            displayLoader(true)
            hideErrorMessage()
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest,
                                     error: WebResourceError) {
            super.onReceivedError(view, request, error)
            displayLoader(false)
            Log.i("WebViewViewModel", "Loading error")
            postDisplayMessage(resourceProvider.getString(R.string.message_network_error))
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            displayLoader(false)
            hideErrorMessage()
            Log.i("WebViewViewModel", "onPageFinished")
        }
    }

    /**
     * Provides webview client
     * This function is accessed via data binding
     * @return Client
     */
    fun getWebviewClient() = Client()


}