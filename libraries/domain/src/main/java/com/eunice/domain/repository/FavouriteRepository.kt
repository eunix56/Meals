package com.eunice.domain.repository

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import kotlinx.coroutines.flow.Flow

/**
 * Created by {EUNICE BAKARE T.} on {6/18/23}
 * Email: {eunice@reach.africa}
 */

interface FavouriteRepository {
    suspend fun fetchFavouriteMeals(): Flow<DataResult<List<Meal>>>

    suspend fun removeMealFromFavourite(mealId: String): Flow<DataResult<String>>
}