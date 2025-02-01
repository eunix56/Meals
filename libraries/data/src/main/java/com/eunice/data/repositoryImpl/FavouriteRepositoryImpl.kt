package com.eunice.data.repositoryImpl

import com.eunice.data.dao.MealDao
import com.eunice.data.mapper.MealEntityModelMapper
import com.eunice.domain.handler.DataResult
import com.eunice.domain.handler.ErrorHandler
import com.eunice.domain.model.Meal
import com.eunice.domain.repository.FavouriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val mealDao: MealDao,
    private val mealEntityModelMapper: MealEntityModelMapper,
    private val errorHandler: ErrorHandler,
) : FavouriteRepository {

    override suspend fun fetchFavouriteMeals(): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        return flow {
            val favMeals = mealDao.fetchFavouriteMeals()
            result = DataResult.Success(mealEntityModelMapper.mapFromEntityList(favMeals))
            emit(result)
        }.catch { throwable ->
            result = DataResult.Error(errorHandler.getError(throwable))
            emit(result)
        }
    }

    override suspend fun removeMealFromFavourite(mealId: String): Flow<DataResult<String>> {
        var result: DataResult<String>
        return flow {
            val favMeals = mealDao.updateMealStatus(mealId, false)
            result = DataResult.Success(favMeals.toString())
            emit(result)
        }.catch { throwable ->
            result = DataResult.Error(errorHandler.getError(throwable))
            emit(result)
        }
    }
}