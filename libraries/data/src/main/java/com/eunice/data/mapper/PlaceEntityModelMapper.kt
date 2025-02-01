package com.eunice.data.mapper

import com.eunice.data.entities.PlaceEntity
import com.eunice.data.mapper.base.EntityModelMapper
import com.eunice.domain.model.Place

/**
 * Created by {EUNICE BAKARE T.} on {3/11/23}
 * Email: {eunice@reach.africa}
 */

class PlaceEntityModelMapper: EntityModelMapper<PlaceEntity, Place> {
    override fun mapFromEntity(entity: PlaceEntity): Place {
        return Place(
            entity.area
        )
    }

    override fun mapToEntity(domain: Place): PlaceEntity {
        return PlaceEntity(
            domain.area
        )
    }
}