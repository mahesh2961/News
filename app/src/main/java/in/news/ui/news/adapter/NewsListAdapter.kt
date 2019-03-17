package `in`.news.ui.news.adapter

import `in`.news.data.db.model.Article
import `in`.news.databinding.ListItemArticleBinding
import `in`.news.ui.news.NewsHostFragmentDirections
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


/**
 * Adapter to Display Article List
 */
class NewsListAdapter() : ListAdapter<Article, NewsListAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        holder.apply {
            bind(createOnClickListener(article), article)
            itemView.tag = article
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(category: Article): View.OnClickListener {
        return View.OnClickListener {
            val direction = NewsHostFragmentDirections.actionCategoryToDetails(category.url)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
            private val binding: ListItemArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Article) {
            binding.apply {
                clickListener = listener
                article = item
                executePendingBindings()
            }
        }
    }
}

/**
 * checks if List contains same objects after notifyDatasetChange
 */
private class DiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title.equals(newItem.title)
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return true
    }
}