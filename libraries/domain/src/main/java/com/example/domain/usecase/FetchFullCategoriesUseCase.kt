package com.example.domain.usecase

import com.example.domain.handler.DataResult
import com.example.domain.model.Category
import com.example.domain.repository.CategoryRepository
import com.example.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 26/04/2022
 * Email: eunice@reach.africa
 */
class FetchFullCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend fun invoke(): Flow<DataResult<List<Category>>> =
        categoryRepository.fetchFullCategories()
}