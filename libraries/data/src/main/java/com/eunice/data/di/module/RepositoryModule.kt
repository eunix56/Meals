package com.eunice.data.di.module

import com.eunice.data.repositoryImpl.CategoryRepositoryImpl
import com.eunice.data.repositoryImpl.IngredientRepositoryImpl
import com.eunice.data.repositoryImpl.MealRepositoryImpl
import com.eunice.domain.repository.CategoryRepository
import com.eunice.domain.repository.IngredientRepository
import com.eunice.domain.repository.MealRepository
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
    val MealRepositoryImpl.bindMealRepository: MealRepository

}