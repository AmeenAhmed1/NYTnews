package com.ameen.nytnews.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.nytnews.databinding.ItemArticleBinding
import com.ameen.nytnews.model.ArticleModel

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var _binding: ItemArticleBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<ArticleModel>() {
        override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = diff.currentList[position]

        holder.binding.ArticleItem.setOnClickListener { onItemClickListener?.let { it(article) } }
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((ArticleModel) -> Unit)? = null
    fun onItemClicked(listener: (ArticleModel) -> Unit) {
        onItemClickListener = listener
    }
}