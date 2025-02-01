package com.eunice.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eunice.domain.model.Meal
import com.eunice.ingredients.databinding.ItemMealWithNameBinding
import com.eunice.view.imageWithPlaceholder

/**
 * Created by {EUNICE BAKARE T.} on {3/23/23}
 * Email: {eunice@reach.africa}
 */

class MealsWithIngredientAdapter(val onClickMeal: (String) -> Unit):
    ListAdapter<Meal, MealsWithIngredientAdapter.MealsWithIngredientViewHolder>(MEALS_DIFFUTILS) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealsWithIngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MealsWithIngredientViewHolder(
            ItemMealWithNameBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealsWithIngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MealsWithIngredientViewHolder(
        private val mealWithNameBinding: ItemMealWithNameBinding
    ): RecyclerView.ViewHolder(mealWithNameBinding.root) {
        fun bind(meal: Meal) = with(mealWithNameBinding) {
            tvMealName.text = meal.mealName
            ivMealImg.imageWithPlaceholder(meal.mealImg, R.drawable.ing)
            root.setOnClickListener {
                onClickMeal(meal.id)
            }
        }
    }
    companion object {
        val MEALS_DIFFUTILS = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }

        }
    }
}