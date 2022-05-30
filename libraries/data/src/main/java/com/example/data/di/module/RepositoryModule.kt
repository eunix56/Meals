package com.example.data.di.module

import com.example.data.repositoryImpl.CategoryRepositoryImpl
import com.example.data.repositoryImpl.IngredientRepositoryImpl
import com.example.data.repositoryImpl.MealRepositoryImpl
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.IngredientRepository
import com.example.domain.repository.MealRepository
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