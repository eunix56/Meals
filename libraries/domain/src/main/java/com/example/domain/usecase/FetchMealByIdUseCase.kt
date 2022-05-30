package com.example.domain.usecase

import com.example.domain.handler.DataResult
import com.example.domain.model.Meal
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by {EUNICE BAKARE T.} on {5/30/22}
 * Email: {eunice@reach.africa}
 */

class FetchMealByIdUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    
    suspend fun invoke(id: String): Flow<DataResult<Meal>> {
        return mealRepository.fetchMealById(id)
    }
}