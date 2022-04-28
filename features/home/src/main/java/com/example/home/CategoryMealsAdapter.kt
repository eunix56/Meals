package com.example.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.model.Meal
import com.example.home.databinding.ItemCategoryMealBinding
import java.lang.reflect.Field

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class CategoryMealsAdapter(private val categoryMeals: List<Meal>):
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
        val context = holder.categoryMealBinding.root.context
        holder.categoryMealBinding.tvMealName.text = categoryMeal.mealName
        Glide.with(context)
            .load(getIcon(categoryMeal.mealName, context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.categoryMealBinding.ivMealImg)
    }

    override fun getItemCount(): Int {
        return categoryMeals.size
    }

    private fun getIcon(mealName: String, context: Context): Drawable? {
        val drawableFields: Array<Field> = Drawable::class.java.fields
        var drawable: Drawable? = null

        for (field in drawableFields) {
            if (field.name.contains(mealName, true))
                drawable = ContextCompat.getDrawable(context, field.getInt(null))
        }

        return drawable
    }
}

class CategoryMealsViewHolder(val categoryMealBinding:
                              ItemCategoryMealBinding):
    RecyclerView.ViewHolder(categoryMealBinding.root)