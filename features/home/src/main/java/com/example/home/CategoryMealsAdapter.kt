package com.example.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.domain.model.Meal
import com.example.home.databinding.ItemCategoryMealBinding
import java.lang.reflect.Field

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class CategoryMealsAdapter(private val categoryMeals: List<Meal>,
                           private val onClickMeal: ((String) -> Unit)):
    RecyclerView.Adapter<CategoryMealsViewHolder>() {
    private val categoryMealList = arrayListOf(
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg"),
        Meal("52959","Baked salmon with fennel & tomatoes",
            "https://www.themealdb.com/images/media/meals/1548772327.jpg")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryMealsViewHolder(ItemCategoryMealBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: CategoryMealsViewHolder, position: Int) {
        val categoryMeal = categoryMeals[position]
        holder.categoryMealBinding.tvMealName.text = categoryMeal.mealName.maxWord(6)

        Glide
            .with(holder.categoryMealBinding.root)
            .load(categoryMeal.mealImg)
            .centerCrop()
            .placeholder(R.drawable.ic_miscellaneous)
            .override(480, 600)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
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