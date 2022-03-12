package com.example.remote.contractImpl

import com.example.data.contract.MealRemote
import com.example.data.entities.MealEntity
import com.example.remote.MealApiService
import com.example.remote.mapper.MealModelEntityMapper

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
class MealRemoteImpl(
    private val mealApiService: MealApiService,
    private val mealModelEntityMapper: MealModelEntityMapper,
): MealRemote {

    override suspend fun fetchMeals(value: String): List<MealEntity> {
        val meals = mealApiService.getSearchedMealRecipes(value)
        return mealModelEntityMapper.mapModelList(meals)
    }

    override suspend fun fetchCategoryMeals(categoryName: String): List<MealEntity> {
        val categoryMeals = mealApiService.getCategoryMealRecipes(categoryName)
        return mealModelEntityMapper.mapModelList(categoryMeals)
    }

    override suspend fun fetchIngredientMeals(ingredientName: String): List<MealEntity> {
        val ingredients = mealApiService.getIngredientMeals(ingredientName)
        return mealModelEntityMapper.mapModelList(ingredients)
    }
}