package com.example.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Category
import com.example.domain.model.Meal
import com.example.recipes.databinding.ItemIngredientBinding
import com.example.recipes.databinding.ItemInstructionsBinding
import com.example.recipes.databinding.ItemMealTitleBinding


/**
 * Created by {EUNICE BAKARE T.} on {5/26/22}
 * Email: {eunice@reach.africa}
 */

class MealDetailsAdapter(private val recipe: Meal) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isIngredient = true
    private var ingredients: List<Ingredient> = emptyList()
    private var instructions: List<Instruction> = emptyList()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TITLE -> MealTitleViewHolder(ItemMealTitleBinding.inflate(inflater))
            INGREDIENTS -> MealIngredientsViewHolder(ItemIngredientBinding.inflate(inflater))
            else -> MealInstructionsViewHolder(ItemInstructionsBinding.inflate(inflater))
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemType = getItemViewType(position)
        val ingredient = ingredients[position]
        val instruction = instructions[position]
        
        when (itemType) {
            TITLE -> if (holder is MealTitleViewHolder) {
                holder.titleBinding.tvMealTitle.text = recipe.mealName
                holder.setIngredient {
                    setIsIngredient(it)
                }
            }
            INGREDIENTS -> if (holder is MealIngredientsViewHolder) {
                holder.ingredientBinding.tvIngredientName.text = ingredient.ingredient
                holder.ingredientBinding.tvIngredientMeasure.text = ingredient.measure
            }
            else -> if (holder is MealInstructionsViewHolder) {
                holder.instructionsBinding.tvInstruction.text = instruction.instruction
                holder.instructionsBinding.tvInstructionsNum.text =
                    holder.instructionsBinding.root.context.getString(R.string.instructions_num, (position + 1).toString())
            }
        }
    }
    
    override fun getItemCount(): Int {
        return if (isIngredient)
            ingredients.size + 1
        else
            instructions.size + 1
    }
    
    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> TITLE
            isIngredient -> INGREDIENTS
            else -> INSTRUCTIONS
        }
    }
    private fun setIsIngredient(showIngredient: Boolean) {
        isIngredient = showIngredient
        
        if (showIngredient) {
            ingredients = convertMealsToIngredients(recipe)
            notifyItemInserted(2)
        } else {
            instructions = convertMealToInstructions(recipe)
            notifyItemInserted(2)
        }
    }
}


class MealTitleViewHolder(val titleBinding: ItemMealTitleBinding):
        RecyclerView.ViewHolder(titleBinding.root) {
    private var isIngredient = true
    
    fun setIngredient(onItemClick: (Boolean) -> Unit) {
        titleBinding.tvIngredients.setOnClickListener {
            if (isIngredient)
                return@setOnClickListener
                
            else isIngredient = true
            
            onItemClick(isIngredient)
        }
        
        titleBinding.tvInstructions.setOnClickListener {
            if (isIngredient)
                isIngredient = false
            else
                return@setOnClickListener
            
            onItemClick(isIngredient)
        }
    }
}

class MealIngredientsViewHolder(val ingredientBinding: ItemIngredientBinding):
        RecyclerView.ViewHolder(ingredientBinding.root)

class MealInstructionsViewHolder(val instructionsBinding: ItemInstructionsBinding):
        RecyclerView.ViewHolder(instructionsBinding.root)

