package com.eunice.data.contract

import com.eunice.data.entities.MealDTO

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
interface MealRemote {
    suspend fun fetchMeals(value: String): List<MealDTO>?

    suspend fun fetchCategoryMeals(categoryName: String): List<MealDTO>

    suspend fun fetchMealsFromPlaces(placeName: String): List<MealDTO>

    suspend fun fetchIngredientMeals(ingredientName: String): List<MealDTO>
    
    suspend fun fetchMealById(id: String): MealDTO
    
    suspend fun fetchRandomMeal(): MealDTO
}