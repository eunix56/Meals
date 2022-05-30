package com.example.data.repositoryImpl

import com.example.data.contract.MealRemote
import com.example.data.handlerImpl.GeneralErrorHandler
import com.example.data.mapper.MealEntityModelMapper
import com.example.domain.handler.DataResult
import com.example.domain.model.Category
import com.example.domain.model.Meal
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
class MealRepositoryImpl @Inject constructor(
    private val mealRemote: MealRemote,
    private val mealEntityModelMapper: MealEntityModelMapper,
    private val errorHandler: GeneralErrorHandler
): MealRepository {

    override suspend fun fetchMeals(value: String): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        return flow {
                val meals = mealRemote.fetchMeals(value)
                result = DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals))
                emit(result)
            }.catch {
                result = DataResult.Error(errorHandler.getError(it))
                emit(result)
            }
        }

    override suspend fun fetchCategoryMeals(category: String): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        return flow {
                val meals = mealRemote.fetchCategoryMeals(category)
            result = DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }

    override suspend fun fetchIngredientMeals(ingredient: String): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        return flow {
            val meals = mealRemote.fetchIngredientMeals(ingredient)
            result = DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }
    
    override suspend fun fetchMealById(id: String): Flow<DataResult<Meal>> {
        var result: DataResult<Meal>
        return flow {
            val meal = mealRemote.fetchMealById(id)
            result = DataResult.Success(mealEntityModelMapper.mapFromEntity(meal))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }

}