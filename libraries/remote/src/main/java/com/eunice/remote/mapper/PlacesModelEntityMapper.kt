package com.eunice.remote.mapper

import com.eunice.data.entities.PlaceEntity
import com.eunice.remote.mapper.base.RemoteModelEntityMapper
import com.eunice.remote.model.Places

/**
 * Created by {EUNICE BAKARE T.} on {3/23/23}
 * Email: {eunice@reach.africa}
 */

class PlacesModelEntityMapper: RemoteModelEntityMapper<Places, PlaceEntity> {
    override fun mapFromModel(model: Places): PlaceEntity {
        return PlaceEntity(
            model.area
        )
    }
}