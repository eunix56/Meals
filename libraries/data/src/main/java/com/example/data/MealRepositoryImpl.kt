package com.example.data

import com.example.data.contract.MealRemote
import com.example.data.entities.MealEntity
import com.example.data.mapper.MealEntityModelMapper
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
    private val mealEntityModelMapper: MealEntityModelMapper
): MealRepository {

    override suspend fun fetchMeals(value: String): Flow<List<Meal>> {
        return flow {
            val meals = mealRemote.fetchMeals(value)
            emit(mealEntityModelMapper.mapFromEntityList(meals))
        }
    }

    override suspend fun fetchCategoryMeals(category: String): Flow<List<Meal>> {
        return flow {
            val categoryMeals = mealRemote.fetchCategoryMeals(category)
            emit(mealEntityModelMapper.mapFromEntityList(categoryMeals))
        }
    }

    override suspend fun fetchIngredientMeals(ingredient: String): Flow<List<Meal>> {
        return flow {
            val ingredientMeals = mealRemote.fetchIngredientMeals(ingredient)
            emit(mealEntityModelMapper.mapFromEntityList(ingredientMeals))
        }
    }

}