package com.eunice.remote.contractImpl

import com.eunice.data.contract.PlaceRemote
import com.eunice.data.entities.PlaceEntity
import com.eunice.remote.MealApiService
import com.eunice.remote.mapper.PlacesModelEntityMapper
import javax.inject.Inject

class PlaceRemoteImpl @Inject constructor(
    private val mealApiService: MealApiService,
    private val placeModelEntityMapper: PlacesModelEntityMapper
) : PlaceRemote {

    override suspend fun fetchAreas(): List<PlaceEntity> {
        val areas = mealApiService.getPlaces().places
        return placeModelEntityMapper.mapModelList(areas)
    }
}