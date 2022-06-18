package com.eunice.data.contract

import com.eunice.data.entities.MealEntity

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
interface MealRemote {
    suspend fun fetchMeals(value: String): List<MealEntity>

    suspend fun fetchCategoryMeals(categoryName: String): List<MealEntity>

    suspend fun fetchIngredientMeals(ingredientName: String): List<MealEntity>
    
    suspend fun fetchMealById(id: String): MealEntity
}