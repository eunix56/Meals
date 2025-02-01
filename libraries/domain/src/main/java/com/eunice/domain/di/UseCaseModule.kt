package com.eunice.domain.di

import com.eunice.domain.repository.*
import com.eunice.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by EUNICE BAKARE T. on 29/04/2022
 * Email: eunice@reach.africa
 */

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @[Provides Singleton]
    fun fetchCategoryMealsUseCase(mealRepository: MealRepository)
            : FetchCategoryMealsUseCase = FetchCategoryMealsUseCase(mealRepository)

    @[Provides Singleton]
    fun fetchCategoriesUseCase(categoryRepository: CategoryRepository)
            : GetCategoryNamesUseCase = GetCategoryNamesUseCase(categoryRepository)

    @[Provides Singleton]
    fun fetchFullCategoriesUseCase(categoryRepository: CategoryRepository)
            : FetchCategoryDataUseCase = FetchCategoryDataUseCase(categoryRepository)

    @[Provides Singleton]
    fun fetchIngredientMealsUseCase(mealRepository: MealRepository)
            : FetchIngredientMealsUseCase = FetchIngredientMealsUseCase(mealRepository)

    @[Provides Singleton]
    fun fetchIngredientUseCase(ingredientRepository: IngredientRepository)
            : FetchIngredientsUseCase = FetchIngredientsUseCase(ingredientRepository)

    @[Provides Singleton]
    fun fetchPlacesMealsUseCase(mealRepository: MealRepository)
            : FetchPlacesMealsUseCase = FetchPlacesMealsUseCase(mealRepository)

    @[Provides Singleton]
    fun fetchAreaUseCase(areaRepository: AreaRepository)
            : FetchPlacesUseCase = FetchPlacesUseCase(areaRepository)

    @[Provides Singleton]
    fun fetchSearchedMealsUseCase(mealRepository: MealRepository)
            : FetchSearchedMealsUseCase = FetchSearchedMealsUseCase(mealRepository)
    
    @[Provides Singleton]
    fun fetchMealById(mealRepository: MealRepository)
            : FetchMealByIdUseCase = FetchMealByIdUseCase(mealRepository)
    
    @[Provides Singleton]
    fun fetchRandomMeal(mealRepository: MealRepository)
            : FetchRandomMealUseCase = FetchRandomMealUseCase(mealRepository)

    @[Provides Singleton]
    fun fetchFavouriteMeal(favouriteRepository: FavouriteRepository):
            FetchFavouriteMealsUseCase = FetchFavouriteMealsUseCase(favouriteRepository)
    
    @[Provides Singleton]
    fun removeMealFromFavourites(favouriteRepository: FavouriteRepository):
            RemoveMealFromFavouritesUseCase = RemoveMealFromFavouritesUseCase(favouriteRepository)

    @[Provides Singleton]
    fun addMealToFavourites(mealRepository: MealRepository):
            AddMealToFavouritesUseCase = AddMealToFavouritesUseCase(mealRepository)
}