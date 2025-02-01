package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
class FetchIngredientMealsUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {

    suspend fun invoke(ingredientName: String): Flow<DataResult<List<Meal>>> {
        return mealRepository.fetchIngredientMeals(ingredientName)
    }
}  