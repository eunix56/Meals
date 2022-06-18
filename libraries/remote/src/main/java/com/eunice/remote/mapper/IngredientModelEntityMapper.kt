package com.eunice.remote.mapper

import com.eunice.data.entities.IngredientEntity
import com.eunice.remote.mapper.base.RemoteModelEntityMapper
import com.eunice.remote.model.IngredientModel

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class IngredientModelEntityMapper():
    RemoteModelEntityMapper<IngredientModel, IngredientEntity> {

    override fun mapFromModel(model: IngredientModel): IngredientEntity {
        return IngredientEntity(
            model.id,
            model.ingredientName,
            model.description,
            model.type
        )
    }
}