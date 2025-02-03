package com.eunice.ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eunice.domain.model.Ingredient
import com.eunice.ingredients.databinding.ItemIngredientsBinding
import com.eunice.ingredients.databinding.ItemIngredientsHeaderBinding
import com.eunice.view.imageWithPlaceholder

/**
 * Created by {EUNICE BAKARE T.} on {3/9/23}
 * Email: {eunice@reach.africa}
 */

const val HEADER = 0
const val ITEMS = 1

class IngredientsAdapter(private val adapter: PlacesAdapter,
                         private val onClickIngredient: (Ingredient) -> Unit)
    : ListAdapter<Ingredient,
        IngredientsAdapter.IngredientsViewHolder>(
    INGREDIENT_DIFFUTIL
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == HEADER)
            IngredientHeaderViewHolder(
                ItemIngredientsHeaderBinding
                    .inflate(inflater, parent, false)
            )
        else IngredientViewHolder(
            ItemIngredientsBinding
                .inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        if (holder is IngredientHeaderViewHolder) {
            holder.bind()
        } else if (holder is IngredientViewHolder) {
            holder.bind(getItem(position - 1))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER
        else ITEMS
    }

    override fun getItemCount(): Int {
        return currentList.size + 1
    }

    open class IngredientsViewHolder(view: View): RecyclerView.ViewHolder(view)

    inner class IngredientHeaderViewHolder(private val headerBinding: ItemIngredientsHeaderBinding):
        IngredientsViewHolder(headerBinding.root) {
            fun bind() = with(headerBinding) {
                rvMealAreas.adapter = adapter
            }
        }

    inner class IngredientViewHolder(private val ingredientsBinding
                                      : ItemIngredientsBinding)
        : IngredientsViewHolder(ingredientsBinding.root) {
            fun bind(ingredient: Ingredient) = with(ingredientsBinding) {
               val imageUrl = "https://www.themealdb.com/images/ingredients/${ingredient.ingredientName}.png"
                ivImg.imageWithPlaceholder(imageUrl, cm.eunice.view.R.drawable.ing)
                tvIngredientName.text = ingredient.ingredientName
                tvIngredientDesc.text = ingredient.description
                root.setOnClickListener {
                    onClickIngredient(ingredient)
                }
            }
        }

    companion object {
        val INGREDIENT_DIFFUTIL = object : DiffUtil.ItemCallback<Ingredient>() {
            override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
                return oldItem == newItem
            }
        }
    }
}