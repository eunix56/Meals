package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Ingredient
import com.eunice.domain.model.Place
import com.eunice.domain.repository.AreaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by {EUNICE BAKARE T.} on {3/11/23}
 * Email: {eunice@reach.africa}
 */

class FetchPlacesUseCase @Inject constructor(
    private val areaRepository: AreaRepository
) {

    suspend operator fun invoke(): Flow<DataResult<List<Place>>> =
        areaRepository.fetchPlaces()
}