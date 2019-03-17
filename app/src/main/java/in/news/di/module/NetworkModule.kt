package `in`.news.di.module


import `in`.news.BuildConfig
import `in`.news.data.network.ApiHandler
import `in`.news.utils.API_HOST
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Provides object for Network Operations
 */
@Module
class NetworkModule {


    @Provides
    @Singleton
    fun buildNetworkApi(): ApiHandler {
        val clientBuilder = OkHttpClient.Builder()


        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.interceptors().add(logging)
        }

        return Retrofit.Builder().client(clientBuilder.build())
                .baseUrl(API_HOST)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiHandler::class.java)

    }

}
