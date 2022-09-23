package com.example.domain.usecase

import com.example.domain.handler.DataResult
import com.example.domain.model.Category
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */

class FetchCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    suspend fun invoke(): Flow<DataResult<List<Category>>> =
        categoryRepository.fetchCategories()
}