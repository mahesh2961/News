package `in`.news.di

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

interface ResourceProvider {

    fun getString(resourceIdentifier: Int, vararg arguments: Any = arrayOf()): String

    fun getStringArray(resourceIdentifier: Int): Array<String>

    fun getInteger(resourceIdentifier: Int): Int

    fun getIntegerArray(resourceIdentifier: Int): Array<Int>

    fun getBoolean(resourceIdentifier: Int): Boolean

    fun getColor(resourceIdentifier: Int): Int

     fun getDrawable(@DrawableRes resourceIdentifier: Int): Drawable?
}