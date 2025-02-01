package com.eunice.domain.repository

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import kotlinx.coroutines.flow.Flow


/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
interface MealRepository {
    suspend fun fetchMeals(value: String): Flow<DataResult<List<Meal>>>

    suspend fun fetchCategoryMeals(category: String): Flow<DataResult<List<Meal>>>

    suspend fun fetchMealsFromPlaces(placeName: String): Flow<DataResult<List<Meal>>>

    suspend fun fetchIngredientMeals(ingredient: String): Flow<DataResult<List<Meal>>>
    
    suspend fun fetchMealById(id: String): Flow<DataResult<Meal>>
    
    suspend fun fetchRandomMeal(): Flow<DataResult<Meal>>

    suspend fun addMealToFavourites(mealId: String): Flow<DataResult<String>>
}