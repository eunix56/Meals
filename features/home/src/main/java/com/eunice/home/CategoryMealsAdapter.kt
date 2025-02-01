package com.eunice.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.eunice.domain.model.Meal
import com.eunice.home.databinding.ItemCategoryMealBinding

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class CategoryMealsAdapter(private val categoryMeals: List<Meal>,
                           private val onClickMeal: ((String) -> Unit)):
    RecyclerView.Adapter<CategoryMealsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryMealsViewHolder(ItemCategoryMealBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        val categoryMeal = categoryMeals[position]
        holder.categoryMealBinding.tvMealName.text = categoryMeal.mealName.maxWord(6)

        val context = holder.categoryMealBinding.root.context
        Glide
            .with(holder.categoryMealBinding.root)
            .load(categoryMeal.mealImg)
            .into(holder.categoryMealBinding.ivMealImg)
        
        holder.categoryMealBinding.root.setOnClickListener {
            onClickMeal(categoryMeal.id)
        }
    }

    override fun getItemCount(): Int {
        return categoryMeals.size
    }

    private fun String.maxWord(max: Int, postfix: String = ""): String = split(" ").let { words ->
        if (words.size < max) return@let this

        words.take(max).joinToString(separator = " ", postfix = postfix).trim()
    }
}

class CategoryMealsViewHolder(val categoryMealBinding:
                              ItemCategoryMealBinding):
    RecyclerView.ViewHolder(categoryMealBinding.root)