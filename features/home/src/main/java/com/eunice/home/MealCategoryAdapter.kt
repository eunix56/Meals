package com.eunice.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eunice.home.databinding.ItemCategoryBinding
import java.lang.reflect.Field

/**
 * Created by EUNICE BAKARE T. on 20/04/2022
 * Email: eunice@reach.africa
 */
class MealCategoryAdapter(private val categoryNames: List<String>,
                          private val selectCategory: (String) -> Unit)
    : RecyclerView.Adapter<MealCategoryAdapter.MealCategoryViewHolder>() {

    private var checkedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MealCategoryViewHolder(ItemCategoryBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: MealCategoryViewHolder, position: Int) {
        val categoryName = categoryNames[position]
        val context = holder.binding.root.context
        Glide.with(context)
            .load(getIcon(categoryName, context))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.ivCategoryImg)

        holder.binding.tvCategoryTitle.text = categoryName
        holder.binding.flBackground.background = ContextCompat.getDrawable(context, cm.eunice.view.R.drawable.bg_grey_card)

        holder.binding.root.setOnClickListener {
            if (checkedPosition == holder.adapterPosition) {
                checkedPosition = RecyclerView.NO_POSITION
            }
            checkedPosition = holder.adapterPosition
            notifyItemChanged(checkedPosition)
            holder.binding.flBackground.background = ContextCompat.getDrawable(context, R.drawable.bg_peach_box)
            selectCategory(categoryNames[checkedPosition])
        }

    }

    private fun getIcon(mealName: String, context: Context): Drawable? {
        val drawableFields: Array<Field> = R.drawable::class.java.fields
        var drawable: Drawable? = null

        for (field in drawableFields) {
            if (field.name.contains(mealName, true))
                drawable = ContextCompat.getDrawable(context, field.getInt(null))
        }

        return drawable
    }

    override fun getItemCount(): Int {
        return categoryNames.size
    }

    inner class MealCategoryViewHolder(val binding: ItemCategoryBinding)
        : RecyclerView.ViewHolder(binding.root)
}