package com.eunice.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunice.domain.model.Meal
import com.eunice.favourites.databinding.ItemFavouriteBinding
import com.eunice.view.loadImage

/**
 * Created by {EUNICE BAKARE T.} on {6/18/23}
 * Email: {eunice@reach.africa}
 */

class FavouriteAdapter(val onClickMeal: (String) -> Unit,
                       val onRemoveFromFavourite: (String) -> Unit):
    ListAdapter<Meal, FavouriteAdapter.FavouriteViewHolder>(FAVOURITE_DIFFUTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(
            ItemFavouriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavouriteViewHolder(private val binding: ItemFavouriteBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(meal: Meal) = with(binding) {
                ivMealImg.loadImage(meal.mealImg)
                tvMealName.text = meal.mealName
                tvMealCategory.text = root.context.getString(R.string.category_with_slash,
                    meal.mealCategory)
                tvMealArea.text = root.context.getString(R.string.area, meal.mealArea)

                root.setOnClickListener {
                    onClickMeal(meal.id)
                }

                ivBookmark.setOnClickListener {
                    onRemoveFromFavourite(meal.id)
                }
            }
        }

    companion object {
        val FAVOURITE_DIFFUTILS = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
                return oldItem == newItem
            }

        }
    }
}