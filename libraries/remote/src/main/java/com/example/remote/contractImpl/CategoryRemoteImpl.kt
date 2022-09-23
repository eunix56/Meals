package com.example.remote.contractImpl

import com.example.data.contract.CategoryRemote
import com.example.data.entities.CategoryEntity
import com.example.remote.MealApiService
import com.example.remote.mapper.CategoryModelEntityMapper
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
class CategoryRemoteImpl @Inject constructor(
    private val apiService: MealApiService,
    private val categoryModelEntityMapper: CategoryModelEntityMapper
): CategoryRemote {

    override suspend fun fetchCategories(): List<CategoryEntity> {
        val categories = apiService.getCategories()
        return categoryModelEntityMapper.mapModelList(categories.meals)
    }

    override suspend fun fetchFullCategories(): List<CategoryEntity> {
        val fullCategories = apiService.getFullCategories()
        return categoryModelEntityMapper.mapModelList(fullCategories.meals)
    }
}