package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by {EUNICE BAKARE T.} on {6/18/23}
 * Email: {eunice@reach.africa}
 */

class RemoveMealFromFavouritesUseCase @Inject constructor(
    private val favouriteRepository: FavouriteRepository
) {
    suspend operator fun invoke(mealId: String): Flow<DataResult<String>> {
        return favouriteRepository.removeMealFromFavourite(mealId)
    }
}