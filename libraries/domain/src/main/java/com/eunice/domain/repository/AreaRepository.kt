package com.eunice.domain.repository

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Place
import kotlinx.coroutines.flow.Flow

/**
 * Created by {EUNICE BAKARE T.} on {3/11/23}
 * Email: {eunice@reach.africa}
 */

interface AreaRepository {
    suspend fun fetchPlaces(): Flow<DataResult<List<Place>>>
}