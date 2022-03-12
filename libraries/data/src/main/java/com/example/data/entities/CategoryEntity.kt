package com.example.data.entities

import com.squareup.moshi.Json

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
data class CategoryEntity(
    @Json(name = "strCategory")
    val categoryName: String
)
