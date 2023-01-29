package com.eunice.data.entities

import com.squareup.moshi.Json

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */

//"idIngredient":"135","strIngredient":"Flaked Almonds","strDescription":null,"strType":null
data class IngredientEntity(
    @Json(name = "idIngredient")
    val id: String,
    @Json(name = "strIngredient")
    val ingredientName: String,
    @Json(name = "strDescription")
    val description: String?,
    @Json(name = "strType")
    val type: String?
)
