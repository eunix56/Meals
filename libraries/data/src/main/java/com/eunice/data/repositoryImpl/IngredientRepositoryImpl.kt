package com.eunice.data.repositoryImpl

import com.eunice.data.contract.IngredientRemote
import com.eunice.data.handlerImpl.GeneralErrorHandler
import com.eunice.data.mapper.IngredientEntityModelMapper
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Ingredient
import com.eunice.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */

class IngredientRepositoryImpl @Inject constructor(
    private val ingredientRemote: IngredientRemote,
    private val ingredientEntityModelMapper: IngredientEntityModelMapper,
    private val errorHandler: GeneralErrorHandler
): IngredientRepository {

    override suspend fun fetchIngredients(): Flow<DataResult<List<Ingredient>>> {
        var result: DataResult<List<Ingredient>>
        return flow {
            val ingredients = ingredientRemote.fetchIngredients()
            result = DataResult.Success(ingredientEntityModelMapper.mapFromEntityList(ingredients))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }
}