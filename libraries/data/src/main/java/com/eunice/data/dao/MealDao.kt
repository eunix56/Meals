package com.eunice.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eunice.data.db.entity.MealEntity

/**
 * Created by {EUNICE BAKARE T.} on {6/10/23}
 * Email: {eunice@reach.africa}
 */

@Dao
interface MealDao {
    /**
     * Fetch category meals by
     * @param categoryName
     */
    @Query("SELECT * FROM " + MealEntity.TABLE_NAME + " where mealCategory = :categoryName")
    suspend fun retrieveCategoryMeals(categoryName: String): List<MealEntity>

    @Query("SELECT * FROM " + MealEntity.TABLE_NAME + " where mealArea = :place")
    suspend fun retrievePlaceMeals(place: String): List<MealEntity>

    @Query("SELECT * FROM " + MealEntity.TABLE_NAME + " where ingredients like :ingredient")
    suspend fun retrieveIngredientMeals(ingredient: String): List<MealEntity>

    @Query("SELECT * FROM " + MealEntity.TABLE_NAME)
    suspend fun retrieveRandomMeal(): MealEntity?

    @Query("SELECT * FROM " + MealEntity.TABLE_NAME + " where id = :mealId")
    suspend fun fetchMealById(mealId: String): MealEntity?

    @Query("SELECT * FROM " + MealEntity.TABLE_NAME + " where favourite = 1")
    suspend fun fetchFavouriteMeals(): List<MealEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeals(meals: List<MealEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(meal: MealEntity)

    /**
     * Update meal favourite status by meal id
     */
    @Query("UPDATE " + MealEntity.TABLE_NAME + " SET favourite = :isFavourite WHERE id = :mealId")
    fun updateMealStatus(mealId: String, isFavourite: Boolean): Int


    @Query("DELETE FROM " + MealEntity.TABLE_NAME)
    fun clearAll()

}