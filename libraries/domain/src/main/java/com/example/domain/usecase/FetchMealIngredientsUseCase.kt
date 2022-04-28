package com.example.domain.usecase

import com.example.domain.model.Ingredient
import com.example.domain.model.Meal
import com.example.domain.repository.IngredientRepository
import com.example.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
class FetchMealIngredientsUseCase(
    private val mealRepository: MealRepository,
    private val ingredientRepository: IngredientRepository
) {

}
