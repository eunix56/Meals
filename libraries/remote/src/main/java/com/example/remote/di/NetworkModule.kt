package com.example.remote.di

import com.example.data.contract.CategoryRemote
import com.example.data.contract.IngredientRemote
import com.example.data.contract.MealRemote
import com.example.data.mapper.CategoryEntityModelMapper
import com.example.data.mapper.IngredientEntityModelMapper
import com.example.data.mapper.MealEntityModelMapper
import com.example.remote.MealApiFactory
import com.example.remote.MealApiService
import com.example.remote.contractImpl.CategoryRemoteImpl
import com.example.remote.contractImpl.IngredientRemoteImpl
import com.example.remote.contractImpl.MealRemoteImpl
import com.example.remote.mapper.CategoryModelEntityMapper
import com.example.remote.mapper.IngredientModelEntityMapper
import com.example.remote.mapper.MealModelEntityMapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by {EUNICE BAKARE T.} on {5/5/22}
 * Email: {eunice@reach.africa}
 */

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @get:[Binds Singleton]
    val CategoryRemoteImpl.bindCategoryRemote: CategoryRemote

    @get:[Binds Singleton]
    val IngredientRemoteImpl.bindIngredientRemote: IngredientRemote

    @get:[Binds Singleton]
    val MealRemoteImpl.bindMealRemote: MealRemote

    companion object {
        @get:[Provides Singleton]
        val provideMoshi: Moshi
            get() = Moshi.Builder()
                .add(KotlinJsonAdapterFactory()).build()

        @[Provides Singleton]
        fun getApiService(moshi: Moshi): MealApiService =
           MealApiFactory.makeApiService(false, moshi)


        @get: [Provides Singleton]
        val fetchCategoryModelMapper = CategoryModelEntityMapper()

        @get: [Provides Singleton]
        val fetchIngredientModelMapper = IngredientModelEntityMapper()

        @get: [Provides Singleton]
        val fetchMealModelMapper = MealModelEntityMapper()
    }
}