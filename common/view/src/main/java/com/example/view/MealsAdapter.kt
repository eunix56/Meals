package com.example.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.model.Meal
import com.example.view.databinding.ItemMealBinding

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class MealsAdapter(
    private val meals: List<Meal>
): RecyclerView.Adapter<MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MealViewHolder(ItemMealBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        Glide.with(holder.mealBinding.root.context)
            .load(meal.mealImg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.mealBinding.ivMealImg)
        holder.mealBinding.tvMealName.text = meal.mealName
        holder.mealBinding.tvMealArea.text = meal.mealArea
        holder.mealBinding.tvMealCategory.text = meal.mealCategory
    }

    override fun getItemCount(): Int {
        return meals.size
    }
}

class MealViewHolder(val mealBinding: ItemMealBinding):
    RecyclerView.ViewHolder(mealBinding.root)