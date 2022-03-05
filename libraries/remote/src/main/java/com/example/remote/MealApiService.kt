package com.example.remote

import com.example.domain.entities.Ingredient
import com.example.domain.entities.Meal
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
interface MealApiService {

    @GET("search.php")
    suspend fun getSearchedMealRecipes(
        @Query("s") searchValue: String
    ): List<Meal>

    @GET("filter.php")
    suspend fun getCategoryMealRecipes(
        @Query("c") category: String
    ): List<Meal>

    @GET("filter.php")
    suspend fun getIngredientMeals(
        @Query("i") ingredient: String
    ): List<Meal>

    @GET("list.php?{fetch}=list")
    suspend fun getCategoryOrIngredients(
        @Path("fetch") fetchValue: String
    )

}