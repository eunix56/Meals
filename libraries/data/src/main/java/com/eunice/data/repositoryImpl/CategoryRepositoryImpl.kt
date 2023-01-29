package com.eunice.data.repositoryImpl

import com.eunice.data.contract.CategoryRemote
import com.eunice.data.handlerImpl.GeneralErrorHandler
import com.eunice.data.mapper.CategoryEntityModelMapper
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Category
import com.eunice.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by EUNICE BAKARE T. on 10/03/2022
 * Email: eunice@reach.africa
 */
class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemote: CategoryRemote,
    private val categoryEntityModelMapper: CategoryEntityModelMapper,
    private val errorHandler: GeneralErrorHandler
): CategoryRepository {

    override suspend fun fetchCategories(): Flow<DataResult<List<Category>>> {
        var result: DataResult<List<Category>>
        return flow {
                val categories = categoryRemote.fetchCategories()
                result = DataResult.Success(categoryEntityModelMapper.mapFromEntityList(categories))
                emit(result)
            }.catch { throwable ->
                result = DataResult.Error(errorHandler.getError(throwable))
                emit(result)
            }
        }

    override suspend fun fetchFullCategories(): Flow<DataResult<List<Category>>> {
        var result: DataResult<List<Category>>

        return flow {
            val categories = categoryRemote.fetchFullCategories()
            result = DataResult.Success(categoryEntityModelMapper.mapFromEntityList(categories))
            emit(result)
        }.catch { throwable ->
            result = DataResult.Error(errorHandler.getError(throwable))
            emit(result)
        }
    }
}


//If I create a different database module, it would contain DAOs and the database
//The daos need access to the model. I can create models in the db module and map to fit in the repo
//The repository needs access to the daos. I can store data into room from the repo and use room as
//single source of truth