package com.example.remote.model

import com.squareup.moshi.Json

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
data class CategoryModel(
    @Json(name = "strCategory")
    val categoryName: String
) {
}