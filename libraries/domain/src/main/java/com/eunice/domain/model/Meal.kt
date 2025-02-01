package com.eunice.domain.model

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */

data class Meal(
    val id: String,
    val mealName: String,
    val mealImg: String,
    val drink: String?,
    val mealCategory: String = "Meal",
    val mealArea: String = "World",
    val mealSteps: String?,
    val mealTags: String?,
    val mealVideo: String?,
    val ingredients: List<IngredientData>,
    val mealSource: String?,
    val mealImgSource: String?,
    val isFavourite: Boolean = false
)
