package com.eunice.domain.repository

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Category
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 05/03/2022
 * Email: eunice@reach.africa
 */
interface CategoryRepository {
    suspend fun fetchCategories(): Flow<DataResult<List<Category>>>

    suspend fun fetchFullCategories(): Flow<DataResult<List<Category>>>
}

//usecases

//Fetch categories and get meals from there
//Allow user to click on ingredient from meal to see more details
//Get meals containing certain ingredients and list them underneath