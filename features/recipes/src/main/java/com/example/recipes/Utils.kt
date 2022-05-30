package com.example.recipes

import com.example.domain.model.Meal


/**
 * Created by {EUNICE BAKARE T.} on {5/26/22}
 * Email: {eunice@reach.africa}
 */

data class Ingredient(
    val ingredient: String?,
    val measure: String?
)

data class Instruction(
    val instruction: String
)
 
fun convertMealsToIngredients(meal: Meal): List<Ingredient> {
    val ingredients: List<Ingredient> = ArrayList()
    
    ingredients.plus(Ingredient(meal.ingredientOne, meal.measureOne))
    ingredients.plus(Ingredient(meal.ingredientTwo, meal.measureTwo))
    ingredients.plus(Ingredient(meal.ingredientThree, meal.measureThree))
    ingredients.plus(Ingredient(meal.ingredientFour, meal.measureFour))
    ingredients.plus(Ingredient(meal.ingredientFive, meal.measureFive))
    ingredients.plus(Ingredient(meal.ingredientSix, meal.measureSix))
    ingredients.plus(Ingredient(meal.ingredientSeven, meal.measureSeven))
    ingredients.plus(Ingredient(meal.ingredientEight, meal.measureEight))
    ingredients.plus(Ingredient(meal.ingredientNine, meal.measureNine))
    ingredients.plus(Ingredient(meal.ingredientTen, meal.measureTen))
    ingredients.plus(Ingredient(meal.ingredientEleven, meal.measureEleven))
    ingredients.plus(Ingredient(meal.ingredientTwelve, meal.measureTwelve))
    ingredients.plus(Ingredient(meal.ingredientThirteen, meal.measureThirteen))
    ingredients.plus(Ingredient(meal.ingredientFourteen, meal.measureFourteen))
    ingredients.plus(Ingredient(meal.ingredientFifteen, meal.measureFifteen))
    ingredients.plus(Ingredient(meal.ingredientSixteen, meal.measureSixteen))
    ingredients.plus(Ingredient(meal.ingredientSeventeen, meal.measureSeventeen))
    ingredients.plus(Ingredient(meal.ingredientEighteen, meal.measureEighteen))
    ingredients.plus(Ingredient(meal.ingredientNineteen, meal.measureNineteen))
    ingredients.plus(Ingredient(meal.ingredientTwenty, meal.measureTwenty))
    
    return ingredients
}

fun convertMealToInstructions(meal: Meal): List<Instruction> {
    val instruction: List<Instruction> = ArrayList()
    val stringInstructions = meal.mealSteps?.split(".") ?: return instruction
    
    for (i in stringInstructions) {
        instruction.plus(Instruction(i.replace("\\r?\\n", "")))
    }
    
    return instruction
}