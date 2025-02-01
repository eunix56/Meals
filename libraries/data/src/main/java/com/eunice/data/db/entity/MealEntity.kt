package com.eunice.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eunice.domain.model.IngredientData
import com.squareup.moshi.Json

/**
 * Created by {EUNICE BAKARE T.} on {6/10/23}
 * Email: {eunice@reach.africa}
 */

@Entity(tableName = MealEntity.TABLE_NAME)
data class MealEntity(
    @PrimaryKey
    val id: String,
    val mealName: String,
    val mealImg: String,
    val drink: String?,
    val mealCategory: String?,
    val mealArea: String?,
    val mealSteps: String?,
    val mealTags: String?,
    val mealVideo: String?,
    val ingredients: List<IngredientData>,
    val mealSource: String?,
    val mealImgSource: String?,
    val favourite: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "meal_table"
    }
}