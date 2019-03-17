package `in`.news.di.module

import `in`.news.di.ResourceProvider
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AndroidResourceProvider
@Inject constructor(@Named("appContext") private val context: Context)
    : ResourceProvider {

    override fun getString(@StringRes resourceIdentifier: Int, vararg arguments: Any): String {
        return if (arguments.isNotEmpty())
            context.resources.getString(resourceIdentifier, *arguments)
        else
            context.resources.getString(resourceIdentifier)
    }

    override fun getStringArray(@ArrayRes resourceIdentifier: Int): Array<String> =
            context.resources.getStringArray(resourceIdentifier)

    override fun getInteger(@IntegerRes resourceIdentifier: Int): Int =
            context.resources.getInteger(resourceIdentifier)

    override fun getIntegerArray(@ArrayRes resourceIdentifier: Int): Array<Int> =
            context.resources.getIntArray(resourceIdentifier).toTypedArray()


    override fun getBoolean(@BoolRes resourceIdentifier: Int): Boolean =
            context.resources.getBoolean(resourceIdentifier)

    override fun getColor(@ColorRes resourceIdentifier: Int): Int =
            ContextCompat.getColor(context, resourceIdentifier)


    override fun getDrawable(@DrawableRes resourceIdentifier: Int): Drawable? =
            ContextCompat.getDrawable(context, resourceIdentifier)

}