package com.eunice.recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eunice.domain.model.Meal
import com.eunice.recipes.databinding.ItemIngredientBinding
import com.eunice.recipes.databinding.ItemInstructionsBinding
import com.eunice.recipes.databinding.ItemMealTitleBinding


/**
 * Created by {EUNICE BAKARE T.} on {5/26/22}
 * Email: {eunice@reach.africa}
 */

class MealDetailsAdapter(private val recipe: Meal) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isIngredient = true
    private var ingredientData: List<IngredientData> = emptyList()
    private var instructions: List<Instruction> = emptyList()
    
    init {
        setIngredientAndInstruction()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TITLE -> MealTitleViewHolder(ItemMealTitleBinding.inflate(inflater))
            INGREDIENTS -> MealIngredientsViewHolder(ItemIngredientBinding.inflate(inflater))
            else -> MealInstructionsViewHolder(ItemInstructionsBinding.inflate(inflater))
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TITLE -> if (holder is MealTitleViewHolder) {
                holder.titleBinding.tvMealTitle.text = recipe.mealName
                holder.titleBinding.tvMealArea.text = recipe.mealArea
                holder.titleBinding.tvMealCategory.text = recipe.mealCategory
                holder.setIngredient {
                    setIsIngredient(it)
                }
            }
            INGREDIENTS -> if (holder is MealIngredientsViewHolder) {
                val ingredient = ingredientData[position - 1]
                
                Glide.with(holder.ingredientBinding.root.context)
                    .load("https://www.themealdb.com/images/ingredients/${ingredient.ingredient}.png")
                    .placeholder(R.drawable.ic_empty_screen)
                    .apply(RequestOptions().override(65, 70))
                    .fitCenter()
                    .into(holder.ingredientBinding.ivIngredientImg)
                
                holder.ingredientBinding.tvIngredientName.text = ingredient.ingredient
                holder.ingredientBinding.tvIngredientMeasure.text = ingredient.measure
            }
            else -> if (holder is MealInstructionsViewHolder) {
                val instruction = instructions[position - 1]
                
                holder.instructionsBinding.tvInstruction.text = instruction.instruction
                holder.instructionsBinding.tvInstructionsNum.text =
                        holder.instructionsBinding.root.context.getString(R.string.instructions_num, ((position).toString()))
            }
        }
    }
    
    override fun getItemCount(): Int {
        return if (isIngredient)
            ingredientData.size + 1
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
        if (showIngredient) {
            notifyItemRangeRemoved(1, instructions.size)
            notifyItemRangeInserted(1, ingredientData.size)
        }
        else {
            notifyItemRangeRemoved(1, ingredientData.size)
            notifyItemRangeInserted(1, instructions.size)
        }
    
        isIngredient = showIngredient
    }
    
    private fun setIngredientAndInstruction() {
        ingredientData = convertMealToIngredients(recipe)
        instructions = convertMealToInstructions(recipe)
    }
}


class MealTitleViewHolder(val titleBinding: ItemMealTitleBinding):
        RecyclerView.ViewHolder(titleBinding.root) {
    private var isIngredient = true
    val context: Context = titleBinding.root.context
    
    fun setIngredient(onItemClick: (Boolean) -> Unit) {
        
        titleBinding.tvIngredients.setOnClickListener {
            if (isIngredient)
                return@setOnClickListener
            else {
                isIngredient = true
                titleBinding.tvIngredients.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_left_selected)
                titleBinding.tvInstructions.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_right)
                titleBinding.tvIngredients.setTextColor(
                    ContextCompat.getColor(context, R.color.mini_text_colour))
                titleBinding.tvInstructions.setTextColor(
                    ContextCompat.getColor(context, R.color.selector_text_colour))
            }
            
            onItemClick(isIngredient)
        }
        
        titleBinding.tvInstructions.setOnClickListener {
            if (isIngredient) {
                isIngredient = false
                titleBinding.tvInstructions.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_right_selected)
                titleBinding.tvInstructions.setTextColor(
                    ContextCompat.getColor(context, R.color.mini_text_colour))
                titleBinding.tvIngredients.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_left)
                titleBinding.tvIngredients.setTextColor(
                    ContextCompat.getColor(context, R.color.selector_text_colour))
            } else
                return@setOnClickListener
            
            onItemClick(isIngredient)
        }
    }
}

class MealIngredientsViewHolder(val ingredientBinding: ItemIngredientBinding):
        RecyclerView.ViewHolder(ingredientBinding.root)

class MealInstructionsViewHolder(val instructionsBinding: ItemInstructionsBinding):
        RecyclerView.ViewHolder(instructionsBinding.root)


class IngredientsDiffUtils(private val oldList: List<IngredientData>,
                           private val newList: List<IngredientData>):
    DiffUtil.Callback() {
    
    override fun getOldListSize(): Int {
        return oldList.size
    }
    
    override fun getNewListSize(): Int {
        return newList.size
    }
    
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].ingredient == newList[newItemPosition].ingredient
    }
    
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].ingredient == newList[newItemPosition].ingredient -> true
            oldList[oldItemPosition].measure == newList[newItemPosition].measure -> true
            else -> false
        }
    }
    
}

class InstructionDiffUtils(private val oldList: List<Instruction>,
                           private val newList: List<Instruction>):
    DiffUtil.Callback() {
    
    override fun getOldListSize(): Int {
        return oldList.size
    }
    
    override fun getNewListSize(): Int {
        return newList.size
    }
    
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].instruction == newList[newItemPosition].instruction
    }
    
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].instruction == newList[newItemPosition].instruction
    }
    
}
