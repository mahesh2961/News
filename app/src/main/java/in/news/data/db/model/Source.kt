package `in`.news.data.db.model

import com.google.gson.annotations.SerializedName

class Source(@SerializedName("id") var sourceId:String?=null,
             @SerializedName("name") var name :String )
{

}