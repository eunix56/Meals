package com.example.domain.usecase

import com.example.domain.handler.DataResult
import com.example.domain.model.Category
import com.example.domain.model.Meal
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */
class FetchSearchedMealsUseCase(
    private val mealRepository: MealRepository
) {

    suspend fun invoke(searchString: String): Flow<DataResult<List<Meal>>> =
        mealRepository.fetchMeals(searchString)

}