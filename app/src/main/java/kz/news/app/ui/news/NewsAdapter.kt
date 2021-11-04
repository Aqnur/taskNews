package kz.news.app.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.news.app.R
import kz.news.app.data.models.Article
import kz.news.app.databinding.AdapterNewsBinding
import kz.news.app.ui_common.callbacks.RecyclerViewItemClickCallback

class NewsAdapter (
    private val recyclerViewItemClickCallback: RecyclerViewItemClickCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is Article && newItem is Article -> {
                    oldItem.title == newItem.title
                }
                else -> {
                    false
                }
            }
        }


        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {

            return when {
                oldItem is Article && newItem is Article -> {
                    oldItem as Article == newItem as Article
                }
                else -> {
                    false
                }
            }
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<Any>) {
        differ.submitList(list)
    }

    companion object {
        private const val VIEW_TYPE_ARTICLE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_ARTICLE -> {
                val binding: AdapterNewsBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.adapter_news,
                        parent,
                        false
                    )
                ArticleViewHolder(binding)
            }
            else -> {
                throw IllegalStateException("Incorrect ViewType found")
            }
        }
    }

    inner class ArticleViewHolder(var binding: AdapterNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun initContent(data: Article) {
            binding.data = data
            binding.recyclerViewItemClickCallback = recyclerViewItemClickCallback
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_ARTICLE -> {
                val viewHolder = holder as ArticleViewHolder
                viewHolder.initContent(differ.currentList[position] as Article)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (differ.currentList[position]) {
            is Article -> VIEW_TYPE_ARTICLE
            else -> throw IllegalStateException("Incorrect ViewType found")
        }
}