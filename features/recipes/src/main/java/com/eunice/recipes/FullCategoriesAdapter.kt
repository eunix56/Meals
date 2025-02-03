package com.eunice.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eunice.domain.model.Category
import com.eunice.recipes.databinding.ItemFullCategoryBinding


/**
 * Created by {EUNICE BAKARE T.} on {6/21/22}
 * Email: {eunice@reach.africa}
 */

class FullCategoriesAdapter(private var fullCategories: List<Category>,
                            val onItemClick: ((Category) -> Unit)) :
    RecyclerView.Adapter<FullCategoryViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FullCategoryViewHolder(ItemFullCategoryBinding.inflate(inflater))
    }
    
    override fun onBindViewHolder(holder: FullCategoryViewHolder, position: Int) {
        val category = fullCategories[position]
        
        Glide.with(holder.fullCategoryBinding.root.context)
            .load(category.categoryImg)
            .placeholder(cm.eunice.view.R.drawable.ic_empty_screen)
            .fitCenter()
            .into(holder.fullCategoryBinding.ivCategoryImg)
        holder.fullCategoryBinding.tvCategoryTitle.text = category.categoryName
        holder.fullCategoryBinding.tvCategoryDetails.text = category.categoryDesc?.maxWord(6)
        
        holder.fullCategoryBinding.root.setOnClickListener {
            onItemClick(category)
        }
    }
    
    override fun getItemCount(): Int {
        return fullCategories.size
    }
    
    private fun String.maxWord(max: Int, postfix: String = ""): String = split(" ").let { words ->
        if (words.size < max) return@let this
        
        words.take(max).joinToString(separator = " ", postfix = postfix).trim()
    }
    
    fun updateCategories(fullCategoryList: List<Category>) {
        notifyItemRangeRemoved(0, fullCategories.size)
        fullCategories = ArrayList(fullCategoryList)
        notifyItemRangeInserted(0, fullCategoryList.size)
    }
}

class FullCategoryViewHolder(val
                             fullCategoryBinding: ItemFullCategoryBinding):
    RecyclerView.ViewHolder(fullCategoryBinding.root) {
    
}