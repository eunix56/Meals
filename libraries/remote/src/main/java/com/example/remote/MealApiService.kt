package com.example.remote

import com.example.remote.model.CategoryModel
import com.example.remote.model.IngredientModel
import com.example.remote.model.MealModel
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
    ): List<MealModel>

    @GET("filter.php")
    suspend fun getCategoryMealRecipes(
        @Query("c") category: String
    ): List<MealModel>

    @GET("filter.php")
    suspend fun getIngredientMeals(
        @Query("i") ingredient: String
    ): List<MealModel>

    @GET("list.php?c=list")
    suspend fun getCategories(): List<CategoryModel>

    @GET("list.php?i=list")
    suspend fun getIngredients(): List<IngredientModel>

}