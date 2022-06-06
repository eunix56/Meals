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
    val ingredients = ArrayList<Ingredient>()
    
    ingredients.add(Ingredient(meal.ingredientOne, meal.measureOne))
    ingredients.add(Ingredient(meal.ingredientTwo, meal.measureTwo))
    ingredients.add(Ingredient(meal.ingredientThree, meal.measureThree))
    ingredients.add(Ingredient(meal.ingredientFour, meal.measureFour))
    ingredients.add(Ingredient(meal.ingredientFive, meal.measureFive))
    ingredients.add(Ingredient(meal.ingredientSix, meal.measureSix))
    ingredients.add(Ingredient(meal.ingredientSeven, meal.measureSeven))
    ingredients.add(Ingredient(meal.ingredientEight, meal.measureEight))
    ingredients.add(Ingredient(meal.ingredientNine, meal.measureNine))
    ingredients.add(Ingredient(meal.ingredientTen, meal.measureTen))
    ingredients.add(Ingredient(meal.ingredientEleven, meal.measureEleven))
    ingredients.add(Ingredient(meal.ingredientTwelve, meal.measureTwelve))
    ingredients.add(Ingredient(meal.ingredientThirteen, meal.measureThirteen))
    ingredients.add(Ingredient(meal.ingredientFourteen, meal.measureFourteen))
    ingredients.add(Ingredient(meal.ingredientFifteen, meal.measureFifteen))
    ingredients.add(Ingredient(meal.ingredientSixteen, meal.measureSixteen))
    ingredients.add(Ingredient(meal.ingredientSeventeen, meal.measureSeventeen))
    ingredients.add(Ingredient(meal.ingredientEighteen, meal.measureEighteen))
    ingredients.add(Ingredient(meal.ingredientNineteen, meal.measureNineteen))
    ingredients.add(Ingredient(meal.ingredientTwenty, meal.measureTwenty))
    
    return ingredients
}

fun convertMealToInstructions(meal: Meal): List<Instruction> {
    val instruction = ArrayList<Instruction>()
    val stringInstructions = meal.mealSteps?.split(".") ?: return instruction
    
    for (i in stringInstructions) {
        instruction.add(Instruction(i.replace("\\r?\\n", "")))
    }
    
    return instruction
}