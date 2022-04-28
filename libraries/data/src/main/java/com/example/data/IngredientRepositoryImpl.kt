package com.example.data

import com.example.data.contract.IngredientRemote
import com.example.data.handlerImpl.GeneralErrorHandler
import com.example.data.mapper.IngredientEntityModelMapper
import com.example.domain.handler.DataResult
import com.example.domain.model.Ingredient
import com.example.domain.model.Meal
import com.example.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */

class IngredientRepositoryImpl(
    private val ingredientRemote: IngredientRemote,
    private val ingredientEntityModelMapper: IngredientEntityModelMapper,
    private val errorHandler: GeneralErrorHandler
): IngredientRepository {

    override suspend fun fetchIngredients(): Flow<DataResult<List<Ingredient>>> {
        return flow {
            try {
                val ingredients = ingredientRemote.fetchIngredients()
                emit(DataResult.Success(ingredientEntityModelMapper.mapFromEntityList(ingredients)))
            } catch (e: Exception) {
                e.cause?.let { DataResult.Error<List<Meal>>(errorHandler.getError(it)) }
            }
        }
    }
}