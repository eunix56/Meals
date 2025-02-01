package com.eunice.remote.contractImpl

import com.eunice.data.contract.CategoryRemote
import com.eunice.data.entities.CategoryEntity
import com.eunice.remote.MealApiService
import com.eunice.remote.mapper.CategoryModelEntityMapper
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
class CategoryRemoteImpl @Inject constructor(
    private val apiService: MealApiService,
    private val categoryModelEntityMapper: CategoryModelEntityMapper
): CategoryRemote {

    override suspend fun fetchCategoryNames(): List<CategoryEntity> {
        val categories = apiService.getCategories()
        return categoryModelEntityMapper.mapModelList(categories.meals)
    }

    override suspend fun fetchCategoryData(): List<CategoryEntity> {
        val fullCategories = apiService.getFullCategories()
        return categoryModelEntityMapper.mapModelList(fullCategories.categories)
    }
}