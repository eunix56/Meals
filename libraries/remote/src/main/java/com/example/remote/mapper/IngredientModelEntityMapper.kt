package com.example.remote.mapper

import com.example.data.entities.IngredientEntity
import com.example.remote.mapper.base.RemoteModelEntityMapper
import com.example.remote.model.IngredientModel

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