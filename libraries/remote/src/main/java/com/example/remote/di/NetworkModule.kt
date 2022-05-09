package com.example.remote.di

import com.example.data.contract.CategoryRemote
import com.example.data.contract.IngredientRemote
import com.example.data.contract.MealRemote
import com.example.remote.MealApiFactory
import com.example.remote.MealApiService
import com.example.remote.contractImpl.CategoryRemoteImpl
import com.example.remote.contractImpl.IngredientRemoteImpl
import com.example.remote.contractImpl.MealRemoteImpl
import com.squareup.moshi.Moshi
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
    val CategoryRemoteImpl.bindRemote: CategoryRemote

    @get:[Binds Singleton]
    val IngredientRemoteImpl.bindRemote: IngredientRemote

    @get:[Binds Singleton]
    val MealRemoteImpl.bindRemote: MealRemote

    companion object {
        @[Provides Singleton]
        fun getApiService(): MealApiService {
            return MealApiFactory.makeApiService(false)
        }
    }
}