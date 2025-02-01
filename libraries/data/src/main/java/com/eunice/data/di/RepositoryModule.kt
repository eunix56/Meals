package com.eunice.data.di

import com.eunice.data.repositoryImpl.*
import com.eunice.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by {EUNICE BAKARE T.} on {5/5/22}
 * Email: {eunicebakare5@gmail.com}
 */

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @get:[Binds Singleton]
    val CategoryRepositoryImpl.bindCategoryRepository: CategoryRepository

    @get:[Binds Singleton]
    val IngredientRepositoryImpl.bindIngredientRepository: IngredientRepository

    @get:[Binds Singleton]
    val AreaRepositoryImpl.bindAreaRepository: AreaRepository

    @get:[Binds Singleton]
    val MealRepositoryImpl.bindMealRepository: MealRepository

    @get:[Binds Singleton]
    val FavouriteRepositoryImpl.bindFavouriteRepository: FavouriteRepository

}