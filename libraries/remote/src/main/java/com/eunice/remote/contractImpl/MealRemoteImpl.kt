package com.eunice.remote.contractImpl

import com.eunice.data.contract.MealRemote
import com.eunice.data.entities.MealEntity
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

    override suspend fun fetchMeals(value: String): List<MealEntity> {
        val meals = mealApiService.getSearchedMealRecipes(value)
        return mealModelEntityMapper.mapModelList(meals.meals)
    }

    override suspend fun fetchCategoryMeals(categoryName: String): List<MealEntity> {
        val categoryMeals = mealApiService.getCategoryMealRecipes(categoryName)
        return mealModelEntityMapper.mapModelList(categoryMeals.meals)
    }

    override suspend fun fetchIngredientMeals(ingredientName: String): List<MealEntity> {
        val ingredients = mealApiService.getIngredientMeals(ingredientName)
        return mealModelEntityMapper.mapModelList(ingredients.meals)
    }
    
    override suspend fun fetchMealById(id: String): MealEntity {
        val meal = mealApiService.getMealById(id)
        return mealModelEntityMapper.mapFromModel(meal.meals.first())
    }
    
    override suspend fun fetchRandomMeal(): MealEntity {
        val meal = mealApiService.getRandomMeal()
        return mealModelEntityMapper.mapFromModel(meal.meals.first())
    }
}