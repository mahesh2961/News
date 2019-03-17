package `in`.news.data.repository

import `in`.news.R
import `in`.news.data.network.model.base.BaseResult
import `in`.news.data.network.exception.AuthenticationException
import `in`.news.di.ResourceProvider
import `in`.news.exception.GenericException
import retrofit2.Response
import java.lang.Exception

/**
 * Base class for Repository Layer
 */
open class BaseRepository(val resourceProvider:ResourceProvider) {

    /**
     * Wrapper function to make api calls and handle responses in generic way
     * This function will return success response with data or Error response with exception
     * @param call
     * @param networkErrorMessage
     */
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, networkErrorMessage: String): BaseResult<T> {

        var response: Response<T>
        try {
            response = call.invoke()
            if (response?.isSuccessful) return BaseResult.Success(response.body()!!)
            else if (response?.code() == 401) return BaseResult.Error(AuthenticationException(resourceProvider.getString(R.string.message_auth_exception)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return BaseResult.Error(GenericException(networkErrorMessage))

    }
}