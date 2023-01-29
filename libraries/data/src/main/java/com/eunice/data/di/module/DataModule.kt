package com.eunice.data.di.module

import com.eunice.data.handlerImpl.GeneralErrorHandler
import com.eunice.data.mapper.CategoryEntityModelMapper
import com.eunice.data.mapper.IngredientEntityModelMapper
import com.eunice.data.mapper.MealEntityModelMapper
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