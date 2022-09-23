package com.example.domain.usecase

import com.example.domain.handler.DataResult
import com.example.domain.model.Ingredient
import com.example.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */
class FetchIngredientsUseCase(
    private val ingredientRepository: IngredientRepository
) {

    suspend fun invoke(): Flow<DataResult<List<Ingredient>>> =
        ingredientRepository.fetchIngredients()

}