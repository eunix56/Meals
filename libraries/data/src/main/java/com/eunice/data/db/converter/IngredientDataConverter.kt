package com.eunice.data.db.converter

import androidx.room.TypeConverter
import com.eunice.data.moshi
import com.eunice.domain.model.IngredientData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Constructor
import javax.inject.Inject

/**
 * Created by {EUNICE BAKARE T.} on {6/12/23}
 * Email: {eunice@reach.africa}
 */

class IngredientDataConverter {

    @TypeConverter
    fun fromIngredientDataJson(ingredient: String?): List<IngredientData>? {
        if (ingredient.isNullOrEmpty()) return emptyList()

        return moshi.adapter<List<IngredientData>>(
            Types.newParameterizedType(
                List::class.java, IngredientData::class.java
            )
        ).fromJson(ingredient)
    }

    @TypeConverter
    fun toIngredientDataJson(ingredient: List<IngredientData>): String {
        if (ingredient.isEmpty()) return ""
        return moshi.adapter<List<IngredientData>>(
            Types.newParameterizedType(
                List::class.java, IngredientData::class.java
            )
        ).toJson(ingredient)
    }
}

