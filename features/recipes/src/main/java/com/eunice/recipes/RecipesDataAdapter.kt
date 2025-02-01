package com.eunice.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eunice.domain.model.Category
import com.eunice.domain.model.Meal
import com.eunice.recipes.databinding.ItemRecipesDataBinding


/**
 * Created by {EUNICE BAKARE T.} on {6/21/22}
 * Email: {eunice@reach.africa}
 */

class RecipesDataAdapter(private val mainRecipe: Meal,
                         private val fullCategories: List<Category>,
                         private val onCategoryItemClick: ((Category) -> Unit),
                         private val onMealItemClick:((Meal) -> Unit),
                         private val onAddToFavourites: (Meal) -> Unit)
    : RecyclerView.Adapter<RecipesViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecipesViewHolder(ItemRecipesDataBinding.inflate(inflater, parent, false))
    }
    
    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.recipesDataBinding.tvMealName.text = mainRecipe.mealName
        holder.recipesDataBinding.tvMealArea.text = mainRecipe.mealArea
        holder.recipesDataBinding.tvMealCategory.text = mainRecipe.mealCategory
        holder.recipesDataBinding.ibBookmark.setOnClickListener {
            onAddToFavourites(mainRecipe)
            holder.recipesDataBinding.ibBookmark.setImageDrawable(
                ContextCompat.getDrawable(it.context, R.drawable.ic_bookmark_filled)
            )
        }
    
        Glide.with(holder.recipesDataBinding.root.context)
            .load(mainRecipe.mealImg)
            .placeholder(R.drawable.ic_empty_screen)
            .into(holder.recipesDataBinding.ivMealImg)
        
        holder.recipesDataBinding.clMealOfDay.setOnClickListener {
            onMealItemClick(mainRecipe)
        }
        
        if (holder.recipesDataBinding.rvFullCategories.adapter == null)
            setCategoriesAdapter(holder)
        else
            updateCategoriesAdapter(holder)
       
    }
    
    override fun getItemCount(): Int {
        return 1
    }
    
    
    private fun setCategoriesAdapter(holder: RecipesViewHolder) {
        val adapter = FullCategoriesAdapter(fullCategories, onCategoryItemClick)
        holder.recipesDataBinding.rvFullCategories.adapter = adapter
    }
    
    private fun updateCategoriesAdapter(holder: RecipesViewHolder) {
        val adapter = holder.recipesDataBinding.rvFullCategories.adapter as FullCategoriesAdapter
        adapter.updateCategories(fullCategories)
    }
}

class RecipesViewHolder(val recipesDataBinding:
                        ItemRecipesDataBinding)
    : RecyclerView.ViewHolder(recipesDataBinding.root) {
    
}