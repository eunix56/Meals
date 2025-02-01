package com.eunice.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunice.ingredients.databinding.ItemOtherAreasBinding

/**
 * Created by {EUNICE BAKARE T.} on {6/5/23}
 * Email: {eunice@reach.africa}
 */

class OtherAreasMealsAdapter(val onClickPlace:(placeName: String) -> Unit):
    ListAdapter<String, OtherAreasMealsAdapter.OtherAreasMealsViewHolder>(
        OTHER_AREAS_DIFFUTILS
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherAreasMealsViewHolder {
        return OtherAreasMealsViewHolder(
            ItemOtherAreasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OtherAreasMealsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class OtherAreasMealsViewHolder(private val binding: ItemOtherAreasBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(area: String) = with(binding) {
                tvMealName.text = area
                root.setOnClickListener { onClickPlace(area) }
            }
        }

    companion object {
        val OTHER_AREAS_DIFFUTILS = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }
}