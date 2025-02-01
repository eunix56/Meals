package com.eunice.data.contract

import com.eunice.data.entities.PlaceEntity

/**
 * Created by {EUNICE BAKARE T.} on {3/11/23}
 * Email: {eunice@reach.africa}
 */

interface PlaceRemote {
    suspend fun fetchAreas(): List<PlaceEntity>
}