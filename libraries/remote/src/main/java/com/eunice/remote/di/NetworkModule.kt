package com.eunice.remote.di

import com.eunice.data.contract.CategoryRemote
import com.eunice.data.contract.IngredientRemote
import com.eunice.data.contract.MealRemote
import com.eunice.data.contract.PlaceRemote
import com.eunice.remote.MealApiFactory
import com.eunice.remote.MealApiService
import com.eunice.remote.contractImpl.CategoryRemoteImpl
import com.eunice.remote.contractImpl.IngredientRemoteImpl
import com.eunice.remote.contractImpl.MealRemoteImpl
import com.eunice.remote.contractImpl.PlaceRemoteImpl
import com.eunice.remote.mapper.CategoryModelEntityMapper
import com.eunice.remote.mapper.IngredientModelEntityMapper
import com.eunice.remote.mapper.MealModelEntityMapper
import com.eunice.remote.mapper.PlacesModelEntityMapper
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

    @get:[Binds Singleton]
    val PlaceRemoteImpl.bindPlaceRemote: PlaceRemote

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

        @get: [Provides Singleton]
        val fetchPlacesModelMapper = PlacesModelEntityMapper()
    }
}