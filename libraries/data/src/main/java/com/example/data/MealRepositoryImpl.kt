package com.example.data

import com.example.data.contract.MealRemote
import com.example.data.handlerImpl.GeneralErrorHandler
import com.example.data.mapper.MealEntityModelMapper
import com.example.domain.handler.DataResult
import com.example.domain.model.Meal
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
class MealRepositoryImpl(
    private val mealRemote: MealRemote,
    private val mealEntityModelMapper: MealEntityModelMapper,
    private val errorHandler: GeneralErrorHandler
): MealRepository {

    override suspend fun fetchMeals(value: String): Flow<DataResult<List<Meal>>> {
        return flow {
            try {
                val meals = mealRemote.fetchMeals(value)
                emit(DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals)))
            } catch (e: Exception) {
                e.cause?.let { DataResult.Error<List<Meal>>(errorHandler.getError(it)) }
            }
        }
    }

    override suspend fun fetchCategoryMeals(category: String): Flow<DataResult<List<Meal>>> {
        return flow {
            try {
                val meals = mealRemote.fetchCategoryMeals(category)
                emit(DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals)))
            } catch (e: Exception) {
                e.cause?.let { Result.failure<List<Meal>>(it) }
            }
        }
    }

    override suspend fun fetchIngredientMeals(ingredient: String): Flow<DataResult<List<Meal>>> {
        return flow {
            try {
                val meals = mealRemote.fetchIngredientMeals(ingredient)
                emit(DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals)))
            } catch (e: Exception) {
                e.cause?.let { Result.failure<List<Meal>>(it) }
            }
        }
    }

}