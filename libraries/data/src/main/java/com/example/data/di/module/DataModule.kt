package com.example.data.di.module

import com.example.data.handlerImpl.GeneralErrorHandler
import com.example.data.mapper.CategoryEntityModelMapper
import com.example.data.mapper.IngredientEntityModelMapper
import com.example.data.mapper.MealEntityModelMapper
import com.example.domain.repository.MealRepository
import com.example.domain.usecase.FetchCategoryMealsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by {EUNICE BAKARE T.} on {5/9/22}
 * Email: {eunice@reach.africa}
 */

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @get:[Provides Singleton]
    val fetchGeneralErrorHandler = GeneralErrorHandler()

    @get: [Provides Singleton]
    val fetchCategoryMapper = CategoryEntityModelMapper()

    @get: [Provides Singleton]
    val fetchIngredientMapper = IngredientEntityModelMapper()

    @get: [Provides Singleton]
    val fetchMealMapper = MealEntityModelMapper()
}