package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by {EUNICE BAKARE T.} on {3/25/23}
 * Email: {eunice@reach.africa}
 */

class FetchPlacesMealsUseCase (
    private val mealRepository: MealRepository
) {

    suspend fun invoke(placeName: String): Flow<DataResult<List<Meal>>> =
        mealRepository.fetchMealsFromPlaces(placeName)

}