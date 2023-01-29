package com.eunice.data.contract

import com.eunice.data.entities.IngredientEntity

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
interface IngredientRemote {
    suspend fun fetchIngredients(): List<IngredientEntity>
}