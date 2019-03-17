package `in`.news.ui.utils

import `in`.news.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * loads image from [imageUrl] in [imageView]
 * @param imageView
 * @param imageUrl
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(imageView: ImageView, imageUrl: String?) {
    val requestOptions = RequestOptions()
    requestOptions.placeholder(R.drawable.placeholder)
    requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)


    Glide.with(imageView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
}







