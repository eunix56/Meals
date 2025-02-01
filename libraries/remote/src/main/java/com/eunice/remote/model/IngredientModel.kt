package com.eunice.remote.model

import com.squareup.moshi.Json

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class IngredientModel(
    @Json(name = "idIngredient")
    val id: String,
    @Json(name = "strIngredient")
    val ingredientName: String,
    @Json(name = "strDescription")
    val description: String?,
    @Json(name = "strType")
    val type: String?
)