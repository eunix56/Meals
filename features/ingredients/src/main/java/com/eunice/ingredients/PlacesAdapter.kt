package com.eunice.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eunice.domain.model.Place
import com.eunice.ingredients.databinding.ItemMealAreasBinding

/**
 * Created by {EUNICE BAKARE T.} on {3/9/23}
 * Email: {eunice@reach.africa}
 */

class PlacesAdapter(val onClick: (String) -> Unit): ListAdapter<Place,
        PlacesAdapter.PlaceViewHolder>(PLACES_DIFFUTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlaceViewHolder(ItemMealAreasBinding.inflate(inflater,
            parent, false))
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlaceViewHolder(private val areasBinding: ItemMealAreasBinding)
        : RecyclerView.ViewHolder(areasBinding.root) {
            fun bind(place: Place) = with(areasBinding) {
                tvMealArea.text = place.area
                root.setOnClickListener {
                    onClick(place.area)
                }
            }
        }

    companion object {
        val PLACES_DIFFUTIL = object : DiffUtil.ItemCallback<Place>() {
            override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem.area == newItem.area
            }

            override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
                return oldItem == newItem
            }

        }
    }
}