package com.example.data.contract

import com.example.data.entities.CategoryEntity


/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
interface CategoryRemote {
    suspend fun fetchCategories(): List<CategoryEntity>
}