package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by {EUNICE BAKARE T.} on {6/18/23}
 * Email: {eunice@reach.africa}
 */

class AddMealToFavouritesUseCase(
    private val mealRepository: MealRepository
) {

    suspend operator fun invoke(mealId: String): Flow<DataResult<String>> {
        return mealRepository.addMealToFavourites(mealId)
    }
}