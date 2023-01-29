package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Ingredient
import com.eunice.domain.repository.IngredientRepository
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