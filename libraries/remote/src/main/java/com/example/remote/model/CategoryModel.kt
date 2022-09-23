package com.example.remote.model

import com.squareup.moshi.Json

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
data class CategoryModel(
    @Json(name = "idCategory")
    val categoryId: String?,
    @Json(name = "strCategory")
    val categoryName: String,
    @Json(name = "strCategoryThumb")
    val categoryImg: String?,
    @Json(name = "strCategoryDescription")
    val categoryDesc: String?
) {
}