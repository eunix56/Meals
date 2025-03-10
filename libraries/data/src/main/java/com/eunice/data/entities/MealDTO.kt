package com.eunice.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by EUNICE BAKARE T. on 02/03/2022
 * Email: eunice@reach.africa
 */

@JsonClass(generateAdapter = true)
data class MealDTO(
    @Json(name = "idMeal")
    val id: String,
    @Json(name = "strMeal")
    val mealName: String,
    @Json(name = "strMealThumb")
    val mealImg: String,
    @Json(name = "strDrinkAlternate")
    val drink: String?,
    @Json(name = "strCategory")
    val mealCategory: String?,
    @Json(name = "strArea")
    val mealArea: String?,
    @Json(name = "strInstructions")
    val mealSteps: String?,
    @Json(name = "strTags")
    val mealTags: String?,
    @Json(name = "strYoutube")
    val mealVideo: String?,
    @Json(name = "strIngredient1")
    val ingredientOne: String?,
    @Json(name = "strIngredient2")
    val ingredientTwo: String?,
    @Json(name = "strIngredient3")
    val ingredientThree: String?,
    @Json(name = "strIngredient4")
    val ingredientFour: String?,
    @Json(name = "strIngredient5")
    val ingredientFive: String?,
    @Json(name = "strIngredient6")
    val ingredientSix: String?,
    @Json(name = "strIngredient7")
    val ingredientSeven: String?,
    @Json(name = "strIngredient8")
    val ingredientEight: String?,
    @Json(name = "strIngredient9")
    val ingredientNine: String?,
    @Json(name = "strIngredient10")
    val ingredientTen: String?,
    @Json(name = "strIngredient11")
    val ingredientEleven: String?,
    @Json(name = "strIngredient12")
    val ingredientTwelve: String?,
    @Json(name = "strIngredient13")
    val ingredientThirteen: String?,
    @Json(name = "strIngredient14")
    val ingredientFourteen: String?,
    @Json(name = "strIngredient15")
    val ingredientFifteen: String?,
    @Json(name = "strIngredient16")
    val ingredientSixteen: String?,
    @Json(name = "strIngredient17")
    val ingredientSeventeen: String?,
    @Json(name = "strIngredient18")
    val ingredientEighteen: String?,
    @Json(name = "strIngredient19")
    val ingredientNineteen: String?,
    @Json(name = "strIngredient20")
    val ingredientTwenty: String?,
    @Json(name = "strMeasure1")
    val measureOne: String?,
    @Json(name = "strMeasure2")
    val measureTwo: String?,
    @Json(name = "strMeasure3")
    val measureThree: String?,
    @Json(name = "strMeasure4")
    val measureFour: String?,
    @Json(name = "strMeasure5")
    val measureFive: String?,
    @Json(name = "strMeasure6")
    val measureSix: String?,
    @Json(name = "strMeasure7")
    val measureSeven: String?,
    @Json(name = "strMeasure8")
    val measureEight: String?,
    @Json(name = "strMeasure9")
    val measureNine: String?,
    @Json(name = "strMeasure10")
    val measureTen: String?,
    @Json(name = "strMeasure11")
    val measureEleven: String?,
    @Json(name = "strMeasure12")
    val measureTwelve: String?,
    @Json(name = "strMeasure13")
    val measureThirteen: String?,
    @Json(name = "strMeasure14")
    val measureFourteen: String?,
    @Json(name = "strMeasure15")
    val measureFifteen: String?,
    @Json(name = "strMeasure16")
    val measureSixteen: String?,
    @Json(name = "strMeasure17")
    val measureSeventeen: String?,
    @Json(name = "strMeasure18")
    val measureEighteen: String?,
    @Json(name = "strMeasure19")
    val measureNineteen: String?,
    @Json(name = "strMeasure20")
    val measureTwenty: String?,
    @Json(name = "strSource")
    val mealSource: String?,
    @Json(name = "strImageSource")
    val mealImgSource: String?
)
