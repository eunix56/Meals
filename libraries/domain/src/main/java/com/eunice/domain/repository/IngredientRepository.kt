package com.eunice.domain.repository

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
interface IngredientRepository {
    suspend fun fetchIngredients(): Flow<DataResult<List<Ingredient>>>
}