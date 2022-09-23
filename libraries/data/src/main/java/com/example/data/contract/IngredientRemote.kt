package com.example.data.contract

import com.example.data.entities.IngredientEntity

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
interface IngredientRemote {
    suspend fun fetchIngredients(): List<IngredientEntity>
}