package `in`.news.data.network.model.base

import `in`.news.utils.STATUS_SUCCESS

open class BaseResponse {
    var status:String ="";
    var message:String?=null
    fun isSuccess():Boolean = status.equals(STATUS_SUCCESS)
}