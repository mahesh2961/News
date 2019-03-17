package `in`.news.data.network.model.base

/**
 * Wrapper calls for Network Response having Success/Error Format
 */
sealed class BaseResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : BaseResult<T>()
    data class Error(val exception: Exception) : BaseResult<Nothing>()
}