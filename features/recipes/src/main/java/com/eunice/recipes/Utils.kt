package com.eunice.recipes

import com.eunice.domain.model.Meal


/**
 * Created by {EUNICE BAKARE T.} on {5/26/22}
 * Email: {eunice@reach.africa}
 */

data class IngredientData(
    val ingredient: String?,
    val measure: String?
)

data class Instruction(
    val instruction: String
)
 
fun convertMealToIngredients(meal: Meal): List<IngredientData> {
    val ingredients = ArrayList<IngredientData>()
    
    ingredients.add(IngredientData(meal.ingredientOne, meal.measureOne))
    ingredients.add(IngredientData(meal.ingredientTwo, meal.measureTwo))
    ingredients.add(IngredientData(meal.ingredientThree, meal.measureThree))
    ingredients.add(IngredientData(meal.ingredientFour, meal.measureFour))
    ingredients.add(IngredientData(meal.ingredientFive, meal.measureFive))
    ingredients.add(IngredientData(meal.ingredientSix, meal.measureSix))
    ingredients.add(IngredientData(meal.ingredientSeven, meal.measureSeven))
    ingredients.add(IngredientData(meal.ingredientEight, meal.measureEight))
    ingredients.add(IngredientData(meal.ingredientNine, meal.measureNine))
    ingredients.add(IngredientData(meal.ingredientTen, meal.measureTen))
    ingredients.add(IngredientData(meal.ingredientEleven, meal.measureEleven))
    ingredients.add(IngredientData(meal.ingredientTwelve, meal.measureTwelve))
    ingredients.add(IngredientData(meal.ingredientThirteen, meal.measureThirteen))
    ingredients.add(IngredientData(meal.ingredientFourteen, meal.measureFourteen))
    ingredients.add(IngredientData(meal.ingredientFifteen, meal.measureFifteen))
    ingredients.add(IngredientData(meal.ingredientSixteen, meal.measureSixteen))
    ingredients.add(IngredientData(meal.ingredientSeventeen, meal.measureSeventeen))
    ingredients.add(IngredientData(meal.ingredientEighteen, meal.measureEighteen))
    ingredients.add(IngredientData(meal.ingredientNineteen, meal.measureNineteen))
    ingredients.add(IngredientData(meal.ingredientTwenty, meal.measureTwenty))
    
    val mainIngredients = ingredients.filterIndexed { _, ingredient ->
        !ingredient.ingredient.isNullOrEmpty()
    }.distinct()
    
    return mainIngredients
}

fun convertMealToInstructions(meal: Meal): List<Instruction> {
    val instruction = ArrayList<Instruction>()
    val stringInstructions = meal.mealSteps?.split(".") ?: return instruction
    
    for (i in stringInstructions) {
        instruction.add(Instruction(i.replace("(\\r\\n)+\\d|\\r\\n|\\d(\\r\\n)+".toRegex(), "")))
    }
    
    val instructions = instruction.filterIndexed {_, i ->
        i.instruction.isNotEmpty()}.distinct()
    
    return instructions
}