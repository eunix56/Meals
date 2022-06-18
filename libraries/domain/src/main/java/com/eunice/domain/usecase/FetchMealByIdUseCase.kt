package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.repository.MealRepository
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