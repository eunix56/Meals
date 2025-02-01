package com.eunice.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunice.domain.model.Meal
import com.eunice.ingredients.databinding.ItemAreaMealsBinding
import com.eunice.view.imageWithPlaceholder

/**
 * Created by {EUNICE BAKARE T.} on {6/6/23}
 * Email: {eunice@reach.africa}
 */

class AreaDetailMealsAdapter:
    ListAdapter<Meal,
            AreaDetailMealsAdapter.AreaDetailMealsViewHolder>(AREA_MEALS_DIFFUTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaDetailMealsViewHolder {
        return AreaDetailMealsViewHolder(
            ItemAreaMealsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AreaDetailMealsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AreaDetailMealsViewHolder(private val binding:
                                       ItemAreaMealsBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(meal: Meal) = with(binding) {
                tvMealName.text = meal.mealName
                ivMealImg.imageWithPlaceholder(meal.mealImg, R.drawable.food)
            }
        }

    companion object {
        val AREA_MEALS_DIFFUTILS = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }

        }
    }
}