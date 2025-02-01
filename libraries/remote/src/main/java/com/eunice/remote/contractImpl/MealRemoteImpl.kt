package com.eunice.remote.contractImpl

import com.eunice.data.contract.MealRemote
import com.eunice.data.entities.MealDTO
import com.eunice.remote.MealApiService
import com.eunice.remote.mapper.MealModelEntityMapper
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
class MealRemoteImpl @Inject constructor(
    private val mealApiService: MealApiService,
    private val mealModelEntityMapper: MealModelEntityMapper,
): MealRemote {

    override suspend fun fetchMeals(value: String): List<MealDTO>? {
        val meals = mealApiService.getSearchedMealRecipes(value)
        return meals?.meals?.let { mealModelEntityMapper.mapModelList(it) }
    }

    override suspend fun fetchCategoryMeals(categoryName: String): List<MealDTO> {
        val categoryMeals = mealApiService.getCategoryMealRecipes(categoryName)
        return mealModelEntityMapper.mapModelList(categoryMeals.meals)
    }

    override suspend fun fetchMealsFromPlaces(placeName: String): List<MealDTO> {
        val placesMeals = mealApiService.getMealsFromPlacesRecipes(placeName)
        return mealModelEntityMapper.mapModelList(placesMeals.meals)
    }

    override suspend fun fetchIngredientMeals(ingredientName: String): List<MealDTO> {
        val ingredients = mealApiService.getIngredientMeals(ingredientName)
        return mealModelEntityMapper.mapModelList(ingredients.meals)
    }
    
    override suspend fun fetchMealById(id: String): MealDTO {
        val meal = mealApiService.getMealById(id)
        return mealModelEntityMapper.mapFromModel(meal.meals.first())
    }
    
    override suspend fun fetchRandomMeal(): MealDTO {
        val meal = mealApiService.getRandomMeal()
        return mealModelEntityMapper.mapFromModel(meal.meals.first())
    }
}