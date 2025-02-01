package com.eunice.data.entities

import com.squareup.moshi.Json

/**
 * Created by {EUNICE BAKARE T.} on {3/11/23}
 * Email: {eunice@reach.africa}
 */

data class PlaceEntity(
    @Json(name = "strArea")
    val area: String
)