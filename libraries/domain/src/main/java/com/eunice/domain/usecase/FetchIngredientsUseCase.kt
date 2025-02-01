package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Ingredient
import com.eunice.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */
class FetchIngredientsUseCase @Inject constructor(
    private val ingredientRepository: IngredientRepository
) {

    suspend operator fun invoke(): Flow<DataResult<List<Ingredient>>> =
        ingredientRepository.fetchIngredients()

}