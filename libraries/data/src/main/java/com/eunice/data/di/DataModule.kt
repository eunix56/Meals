package com.eunice.data.di

import android.content.Context
import com.eunice.data.dao.MealDao
import com.eunice.data.db.MealsDb
import com.eunice.data.handlerImpl.GeneralErrorHandler
import com.eunice.data.mapper.CategoryEntityModelMapper
import com.eunice.data.mapper.IngredientEntityModelMapper
import com.eunice.data.mapper.MealEntityModelMapper
import com.eunice.data.mapper.PlaceEntityModelMapper
import com.eunice.domain.handler.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by {EUNICE BAKARE T.} on {5/9/22}
 * Email: {eunice@reach.africa}
 */

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @get:[Binds Singleton]
    val GeneralErrorHandler.bindErrorHandler: ErrorHandler

    companion object {
        @get:[Provides Singleton]
        val fetchGeneralErrorHandler = GeneralErrorHandler()

        @get: [Provides Singleton]
        val fetchCategoryMapper = CategoryEntityModelMapper()

        @get: [Provides Singleton]
        val fetchIngredientMapper = IngredientEntityModelMapper()

        @get: [Provides Singleton]
        val fetchPlaceMapper = PlaceEntityModelMapper()

        @get: [Provides Singleton]
        val fetchMealMapper = MealEntityModelMapper()

        @[Provides Singleton]
        fun provideMealDao(@ApplicationContext context: Context): MealDao {
            return MealsDb.getDb(context).mealDao()
        }
    }
}