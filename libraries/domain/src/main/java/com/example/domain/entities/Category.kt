package com.example.domain.entities

import com.squareup.moshi.Json

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
data class Category(
    @Json(name = "strCategory")
    val categoryName: String
)
