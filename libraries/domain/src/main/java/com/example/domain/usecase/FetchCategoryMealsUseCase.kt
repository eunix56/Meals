package com.example.domain.usecase

import com.example.domain.handler.DataResult
import com.example.domain.model.Meal
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
class FetchCategoryMealsUseCase(
    private val mealRepository: MealRepository
) {

    suspend fun invoke(categoryName: String): Flow<DataResult<List<Meal>>> {
        return mealRepository.fetchCategoryMeals(categoryName)
    }


}