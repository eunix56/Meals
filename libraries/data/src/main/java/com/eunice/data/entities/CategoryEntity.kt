package com.eunice.data.entities

import com.squareup.moshi.Json

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
data class CategoryEntity(
    @Json(name = "idCategory")
    val categoryId: String?,
    @Json(name = "strCategory")
    val categoryName: String,
    @Json(name = "strCategoryThumb")
    val categoryImg: String?,
    @Json(name = "strCategoryDescription")
    val categoryDesc: String?
)
