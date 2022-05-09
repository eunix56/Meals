package com.example.data.di

import com.example.data.CategoryRepositoryImpl
import com.example.data.IngredientRepositoryImpl
import com.example.data.MealRepositoryImpl
import com.example.data.contract.CategoryRemote
import com.example.data.contract.IngredientRemote
import com.example.data.contract.MealRemote
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

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @get:[Binds Singleton]
    val CategoryRepositoryImpl.bindRepository: CategoryRepository

    @get:[Binds Singleton]
    val IngredientRepositoryImpl.bindRepository: IngredientRepository

    @get:[Binds Singleton]
    val MealRepositoryImpl.bindRepository: MealRepository

}