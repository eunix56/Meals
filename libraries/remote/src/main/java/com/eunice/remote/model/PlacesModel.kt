package com.eunice.remote.model

import com.squareup.moshi.Json

/**
 * Created by {EUNICE BAKARE T.} on {3/11/23}
 * Email: {eunice@reach.africa}
 */

class PlacesModel(
    @Json(name = "meals")
    val places: List<Places>
)

class Places(
    @Json(name = "strArea")
    val area: String
)