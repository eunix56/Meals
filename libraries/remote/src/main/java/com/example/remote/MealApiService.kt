package com.example.remote

import com.example.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
interface MealApiService {

    @GET("search.php")
    suspend fun getSearchedMealRecipes(
        @Query("s") searchValue: String
    ): MealListModel

    @GET("filter.php")
    suspend fun getCategoryMealRecipes(
        @Query("c") category: String
    ): MealListModel

    @GET("filter.php")
    suspend fun getIngredientMeals(
        @Query("i") ingredient: String
    ): MealListModel

    @GET("list.php?c=list")
    suspend fun getCategories(): CategoryMealsModel

    @GET("list.php?i=list")
    suspend fun getIngredients(): IngredientMealsModel

    @GET("categories.php")
    suspend fun getFullCategories(): CategoryMealsModel

    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") id: String
    ): MealListModel
    
}