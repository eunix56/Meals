package com.eunice.data.repositoryImpl

import com.eunice.data.contract.MealRemote
import com.eunice.data.dao.MealDao
import com.eunice.data.db.entity.MealEntity
import com.eunice.data.handlerImpl.GeneralErrorHandler
import com.eunice.data.mapper.MealEntityModelMapper
import com.eunice.domain.handler.DataResult
import com.eunice.domain.handler.ErrorResult
import com.eunice.domain.model.Meal
import com.eunice.domain.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
class MealRepositoryImpl @Inject constructor(
    private val mealRemote: MealRemote,
    private val mealDao: MealDao,
    private val mealEntityModelMapper: MealEntityModelMapper,
    private val errorHandler: GeneralErrorHandler
): MealRepository {

    override suspend fun fetchMeals(value: String): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        return flow {
            val meals = mealRemote.fetchMeals(value)
            if (meals == null) {
                result = DataResult.Error(ErrorResult.NotFound)
                emit(result)
                return@flow
            }
            result = DataResult.Success(meals.map { mealEntityModelMapper.mapToDomain(it) })
            mealDao.insertMeals(meals.map { mealEntityModelMapper.mapFromDTO(it) })
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }

    override suspend fun fetchCategoryMeals(category: String): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        var meals: List<MealEntity>
        return flow {
            meals = mealDao.retrieveCategoryMeals(category)
            if (meals.isEmpty()) {
                meals = mealRemote.fetchCategoryMeals(category).map {
                    mealEntityModelMapper.mapFromDTO(it)
                }
                withContext(Dispatchers.IO) {
                    mealDao.insertMeals(meals)
                }
            }
            result = DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }

    override suspend fun fetchMealsFromPlaces(placeName: String): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        var meals: List<MealEntity>
        return flow {
            meals = mealDao.retrievePlaceMeals(placeName)
            if (meals.isEmpty()) {
                meals = mealRemote.fetchMealsFromPlaces(placeName).map {
                    mealEntityModelMapper.mapFromDTO(it)
                }
                withContext(Dispatchers.IO) {
                    mealDao.insertMeals(meals)
                }
            }
            result = DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }

    override suspend fun fetchIngredientMeals(ingredient: String): Flow<DataResult<List<Meal>>> {
        var result: DataResult<List<Meal>>
        var meals: List<MealEntity>
        return flow {
            meals = mealDao.retrieveIngredientMeals(ingredient)
            if (meals.isEmpty()) {
                meals = mealRemote.fetchIngredientMeals(ingredient).map {
                    mealEntityModelMapper.mapFromDTO(it)
                }
                withContext(Dispatchers.IO) {
                    mealDao.insertMeals(meals)
                }
            }
            result = DataResult.Success(mealEntityModelMapper.mapFromEntityList(meals))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }
    
    override suspend fun fetchMealById(id: String): Flow<DataResult<Meal>> {
        var result: DataResult<Meal>
        var meal: MealEntity?
        return flow {
            meal = mealDao.fetchMealById(id)
            if (meal == null || meal?.ingredients?.isEmpty() == true) {
                meal = mealEntityModelMapper.mapFromDTO(mealRemote.fetchMealById(id))
                withContext(Dispatchers.IO) {
                    mealDao.insertMeal(meal!!)
                }
            }
            result = DataResult.Success(mealEntityModelMapper.mapFromEntity(meal!!))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }
    
    override suspend fun fetchRandomMeal(): Flow<DataResult<Meal>> {
        var result: DataResult<Meal>
        var meal: MealEntity?
        return flow {
            meal = mealDao.retrieveRandomMeal()
            if (meal == null) {
                meal = mealEntityModelMapper.mapFromDTO(mealRemote.fetchRandomMeal())
                withContext(Dispatchers.IO) {
                    mealDao.insertMeal(meal!!)
                }
            }
            result = DataResult.Success(mealEntityModelMapper.mapFromEntity(meal!!))
            emit(result)
        }.catch {
            result = DataResult.Error(errorHandler.getError(it))
            emit(result)
        }
    }

    override suspend fun addMealToFavourites(mealId: String): Flow<DataResult<String>> {
        var result: DataResult<String>
        return flow {
            val successfulRows = withContext(Dispatchers.IO) {
                mealDao.updateMealStatus(mealId, true)
            }
            result = DataResult.Success(successfulRows.toString())
            emit(result)
        }.catch { throwable ->
            result = DataResult.Error(errorHandler.getError(throwable))
            emit(result)
        }
    }

}