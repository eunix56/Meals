package com.eunice.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.eunice.domain.model.Meal
import com.eunice.recipes.databinding.ItemSearchHeaderBinding
import com.eunice.recipes.databinding.ItemSearchedMealBinding

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class SearchedMealsAdapter(private val searchedMeals: List<Meal>,
                           private val onAddToFavourites: (Meal) -> Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> SearchHeaderViewHolder(ItemSearchHeaderBinding.inflate(inflater, parent, false))
            else -> SearchedMealsViewHolder(ItemSearchedMealBinding.inflate(inflater, parent, false))
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            RESULTS -> if (holder is SearchedMealsViewHolder) {
                val searchedMeal = searchedMeals[position - 1]
                
                Glide.with(holder.searchedMealBinding.root.context)
                    .load(searchedMeal.mealImg)
                    .placeholder(R.drawable.ic_empty_screen)
                    .into(holder.searchedMealBinding.ivMealImg)
                holder.searchedMealBinding.tvMealName.text = searchedMeal.mealName
                holder.searchedMealBinding.tvMealArea.text = searchedMeal.mealArea
                holder.searchedMealBinding.tvMealCategory.text = searchedMeal.mealCategory
                holder.searchedMealBinding.ibBookmark.setOnClickListener {
                    onAddToFavourites(searchedMeal)
                    holder.searchedMealBinding.ibBookmark.setImageDrawable(
                        ContextCompat.getDrawable(it.context, R.drawable.ic_bookmark_filled)
                    )
                }
                
                
            }
        }
    }
    
    override fun getItemCount(): Int {
        return searchedMeals.size + 1
    }
    
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER
            else -> RESULTS
        }
    }
    
}

class SearchHeaderViewHolder(searchHeaderBinding: ItemSearchHeaderBinding):
    RecyclerView.ViewHolder(searchHeaderBinding.root)

class SearchedMealsViewHolder(val searchedMealBinding:
                              ItemSearchedMealBinding):
    RecyclerView.ViewHolder(searchedMealBinding.root)