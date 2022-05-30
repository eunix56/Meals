package com.example.remote.contractImpl

import com.example.data.contract.IngredientRemote
import com.example.data.entities.IngredientEntity
import com.example.remote.MealApiService
import com.example.remote.mapper.IngredientModelEntityMapper

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
class IngredientRemoteImpl(
    private val mealApiService: MealApiService,
    private val ingredientModelEntityMapper: IngredientModelEntityMapper
): IngredientRemote {

    override suspend fun fetchIngredients(): List<IngredientEntity> {
        val ingredients = mealApiService.getIngredients()
        return ingredientModelEntityMapper.mapModelList(ingredients.meals)
    }
}