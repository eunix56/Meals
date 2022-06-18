package com.eunice.remote.contractImpl

import com.eunice.data.contract.IngredientRemote
import com.eunice.data.entities.IngredientEntity
import com.eunice.remote.MealApiService
import com.eunice.remote.mapper.IngredientModelEntityMapper

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