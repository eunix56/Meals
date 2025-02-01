package com.eunice.domain.usecase

import com.eunice.domain.handler.DataResult
import com.eunice.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 12/03/2022
 * Email: eunice@reach.africa
 */

class GetCategoryNamesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke(): Flow<DataResult<List<String>>> =
        categoryRepository.fetchCategoryNames()
}