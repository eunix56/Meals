package com.eunice.data.contract

import com.eunice.data.entities.CategoryEntity


/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
interface CategoryRemote {
    suspend fun fetchCategories(): List<CategoryEntity>

    suspend fun fetchFullCategories(): List<CategoryEntity>
}