package com.example.domain.di

import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.IngredientRepository
import com.example.domain.repository.MealRepository
import com.example.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by EUNICE BAKARE T. on 29/04/2022
 * Email: eunice@reach.africa
 */

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun fetchCategoryMealsUseCase(mealRepository: MealRepository)
    : FetchCategoryMealsUseCase = FetchCategoryMealsUseCase(mealRepository)

    @Provides
    @Singleton
    fun fetchCategoriesUseCase(categoryRepository: CategoryRepository)
    : FetchCategoriesUseCase = FetchCategoriesUseCase(categoryRepository)

    @Provides
    @Singleton
    fun fetchFullCategoriesUseCase(categoryRepository: CategoryRepository)
    : FetchFullCategoriesUseCase = FetchFullCategoriesUseCase(categoryRepository)

    @Provides
    @Singleton
    fun fetchIngredientMealsUseCase(mealRepository: MealRepository)
    : FetchIngredientMealsUseCase = FetchIngredientMealsUseCase(mealRepository)

    @Provides
    @Singleton
    fun fetchIngredientUseCase(ingredientRepository: IngredientRepository)
    : FetchIngredientsUseCase = FetchIngredientsUseCase(ingredientRepository)

    @Provides
    @Singleton
    fun fetchSearchedMealsUseCase(mealRepository: MealRepository)
    : FetchSearchedMealsUseCase = FetchSearchedMealsUseCase(mealRepository)


}