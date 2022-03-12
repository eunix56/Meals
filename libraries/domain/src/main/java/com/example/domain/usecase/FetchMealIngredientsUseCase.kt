package com.example.domain.usecase

import com.example.domain.repository.IngredientRepository
import com.example.domain.repository.MealRepository

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
class FetchMealIngredientsUseCase(
    private val mealRepository: MealRepository,
    private val ingredientRepository: IngredientRepository
) {

}
