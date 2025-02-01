package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Category
import com.eunice.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by EUNICE BAKARE T. on 26/04/2022
 * Email: eunice@reach.africa
 */
class FetchCategoryDataUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke(): Flow<DataResult<List<Category>>> =
        categoryRepository.fetchCategoryData()
}