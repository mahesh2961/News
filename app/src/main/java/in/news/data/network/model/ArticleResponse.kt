package `in`.news.data.network.model

import `in`.news.data.db.model.Article
import `in`.news.data.network.model.base.BaseResponse
import com.google.gson.annotations.SerializedName

class ArticleResponse:BaseResponse()
{
    @SerializedName("articles")
    var articles:MutableList<Article> = mutableListOf()
}