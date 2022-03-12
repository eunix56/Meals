package com.example.data

import com.example.data.contract.IngredientRemote
import com.example.data.mapper.IngredientEntityModelMapper
import com.example.domain.model.Ingredient
import com.example.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */

class IngredientRepositoryImpl(
    private val ingredientRemote: IngredientRemote,
    private val ingredientEntityModelMapper: IngredientEntityModelMapper
): IngredientRepository {

    override suspend fun fetchIngredients(): Flow<List<Ingredient>> {
        return flow {
            val ingredients = ingredientRemote.fetchIngredients()
            emit(ingredientEntityModelMapper.mapFromEntityList(ingredients))
        }
    }
}