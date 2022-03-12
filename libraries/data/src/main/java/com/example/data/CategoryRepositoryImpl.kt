package com.example.data

import com.example.data.contract.CategoryRemote
import com.example.data.mapper.CategoryEntityModelMapper
import com.example.domain.model.Category
import com.example.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
class CategoryRepositoryImpl(
    private val categoryRemote: CategoryRemote,
    private val categoryEntityModelMapper: CategoryEntityModelMapper
): CategoryRepository {

    override suspend fun fetchCategories(): Flow<List<Category>> {
        return flow {
            val categories = categoryRemote.fetchCategories()
            emit(categoryEntityModelMapper.mapFromEntityList(categories))
        }
    }

}

//If I create a different database module, it would contain DAOs and the database
//The daos need access to the model. I can create models in the db module and map to fit in the repo
//The repository needs access to the daos. I can store data into room from the repo and use room as
//single source of truth