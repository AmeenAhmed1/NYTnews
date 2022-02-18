package com.ameen.nytnews.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.nytnews.data.model.ArticleResult
import com.ameen.nytnews.databinding.ItemArticleBinding
import com.bumptech.glide.Glide

class ArticleAdapter(private val context: Context) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private val TAG = "ArticleAdapter"

    inner class ArticleViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemArticleBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<ArticleResult>() {
        override fun areItemsTheSame(oldItem: ArticleResult, newItem: ArticleResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleResult, newItem: ArticleResult): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        _binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = diff.currentList[position]
        val articleImage = article.media.firstOrNull()?.media_metadata?.last()?.url

        Log.i(TAG, "onBindViewHolder: Adapter -> ${article.title}")

        holder.binding.apply {
            textTitle.text = article.title
            textAuthor.text = article.source
            textDate.text = article.published_date
        }

        Glide.with(context)
            .load(articleImage)
            .centerCrop()
            .into(holder.binding.imageArticle)


        holder.binding.ArticleItem.setOnClickListener { onItemClickListener?.let { it(article) } }
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((ArticleResult) -> Unit)? = null
    fun onItemClicked(listener: (ArticleResult) -> Unit) {
        onItemClickListener = listener
    }
}