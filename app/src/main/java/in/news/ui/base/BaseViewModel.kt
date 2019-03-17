package `in`.news.ui.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {


    var displayMessage = ObservableField<Boolean>()

    var displayLoader = ObservableField<Boolean>()

    var message = ObservableField<String>()


    /**
     * sets boolean [displayMessage] used in data binding to display message
     * @param showMessage
     */
    fun setShowMessage(showMessage: Boolean?) {
        this.displayMessage.set(showMessage)
    }

    /**
     * sets boolean [displayLoader] used in data binding to display loading progress
     * @param showMessage
     */
    fun displayLoader(boolean: Boolean)
    {
        this.displayLoader.set(boolean)
    }

    /**
     * Set [message] to displayed on screen in case of error
     * Used via Data binding
     * @param message
     */
    fun postDisplayMessage(message: String?) {
        setShowMessage(true)
        this.message.set(message)
    }


    /**
     * Hides error [message] displayed on screen
     * Used via Data binding
     */
    fun hideErrorMessage() {
        setShowMessage(false)
        this.message.set(null)
    }

}