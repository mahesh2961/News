package `in`.news.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

@Entity(tableName = "Article")
data class Article(
        @PrimaryKey(autoGenerate = true)  var id:Int,
        @SerializedName("author")
        var author: String?=null,
        @SerializedName("title")
        var title:String,
        @SerializedName("description")
        var description:String?=null,
        @SerializedName("url")
        var url:String,
        @SerializedName("publishedAt")
        var publishedAt:Timestamp,
        @SerializedName("content")
        var content:String?=null,
        var category:String,
        @SerializedName("source")
        @Embedded var source : Source,
        var elapsedTime:String ="",
        @SerializedName("urlToImage")
        var urlToImage:String?=null) {

    override fun equals(other: Any?): Boolean {
        if(other is Article) return other.id==this.id
        return super.equals(other)
    }

}