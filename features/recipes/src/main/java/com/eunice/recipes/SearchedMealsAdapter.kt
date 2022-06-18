package com.eunice.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eunice.domain.model.Meal
import com.eunice.recipes.databinding.ItemSearchedMealBinding

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class SearchedMealsAdapter(private val searchedMeals: List<Meal>):
    RecyclerView.Adapter<SearchedMealsViewHolder>() {
    private val searchedMealList = arrayListOf(
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedMealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchedMealsViewHolder(ItemSearchedMealBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: SearchedMealsViewHolder, position: Int) {
        val categoryMeal = searchedMeals[position]
        Glide.with(holder.searchedMealBinding.root.context)
            .load(categoryMeal.mealImg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.searchedMealBinding.ivMealImg)
        holder.searchedMealBinding.tvMealName.text = categoryMeal.mealName
    }

    override fun getItemCount(): Int {
        return searchedMeals.size
    }
}

class SearchedMealsViewHolder(val searchedMealBinding:
                              ItemSearchedMealBinding):
    RecyclerView.ViewHolder(searchedMealBinding.root)