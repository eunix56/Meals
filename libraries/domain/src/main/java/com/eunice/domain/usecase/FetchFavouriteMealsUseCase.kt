package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by {EUNICE BAKARE T.} on {6/18/23}
 * Email: {eunice@reach.africa}
 */

class FetchFavouriteMealsUseCase(
    private val favouriteRepository: FavouriteRepository
) {

    suspend operator fun invoke(): Flow<DataResult<List<Meal>>> =
        favouriteRepository.fetchFavouriteMeals()

}