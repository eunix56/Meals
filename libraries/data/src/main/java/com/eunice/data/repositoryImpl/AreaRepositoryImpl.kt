package com.eunice.data.repositoryImpl

import com.eunice.data.contract.PlaceRemote
import com.eunice.data.handlerImpl.GeneralErrorHandler
import com.eunice.data.mapper.PlaceEntityModelMapper
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Place
import com.eunice.domain.repository.AreaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AreaRepositoryImpl @Inject constructor(
    private val placeRemote: PlaceRemote,
    private val placeEntityModelMapper: PlaceEntityModelMapper,
    private val errorHandler: GeneralErrorHandler
) : AreaRepository {
    override suspend fun fetchPlaces(): Flow<DataResult<List<Place>>> {
        var result: DataResult<List<Place>>
        return flow {
            val places = placeRemote.fetchAreas()
            result = DataResult.Success(placeEntityModelMapper.mapFromEntityList(places))
            emit(result)
        }.catch { throwable ->
            result = DataResult.Error(errorHandler.getError(throwable))
            emit(result)
        }
    }
}