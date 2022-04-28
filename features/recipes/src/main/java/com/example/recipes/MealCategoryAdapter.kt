package com.example.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.model.Category
import com.example.recipes.databinding.ItemCategoryBinding
import java.util.zip.Inflater

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class MealCategoryAdapter(private val categories: List<Category>,
                          val listener: MealCategory)
    : RecyclerView.Adapter<MealCategoryViewHolder>() {

    private var categoryList = arrayListOf(
        Category("43527", "Beef",
            "https://www.themealdb.com/images/media/meals/1525873040.jpg", "Love it"),
        Category("43523", "Fish",
            "https://www.themealdb.com/images/media/meals/uwxusv1487344500.jpg", "Love it"),
        Category("43527", "Egg",
            "https://www.themealdb.com/images/media/meals/2dsltq1560461468.jpg", "Love it")
    )

    private var checkedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MealCategoryViewHolder(ItemCategoryBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: MealCategoryViewHolder, position: Int) {
        val category = categories[position]

        Glide.with(holder.binding.root.context)
            .load(category.categoryImg)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.ivCategoryImg)

        holder.binding.tvCategoryTitle.text = category.categoryName

        holder.binding.root.setOnClickListener {
            if (checkedPosition == holder.adapterPosition) {
                checkedPosition = RecyclerView.NO_POSITION
                notifyItemChanged(checkedPosition)
            }
            checkedPosition = holder.adapterPosition
            notifyItemChanged(checkedPosition)
            listener.selectCategory(categories[checkedPosition])
        }

    }

    override fun getItemCount(): Int {
        return categories.size
    }
}

class MealCategoryViewHolder(val binding: ItemCategoryBinding)
    : RecyclerView.ViewHolder(binding.root)

interface MealCategory {
    fun selectCategory(category: Category)
}