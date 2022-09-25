package io.jyryuitpro.shoppi.android.review.ui.categorydetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.jyryuitpro.shoppi.android.review.Product
import io.jyryuitpro.shoppi.android.review.databinding.ItemCategoryPromotionBinding

class CategoryPromotionAdapter : ListAdapter<Product, CategoryPromotionAdapter.CategoryPromotionViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPromotionViewHolder {
        val binding = ItemCategoryPromotionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryPromotionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryPromotionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryPromotionViewHolder(private val binding: ItemCategoryPromotionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}