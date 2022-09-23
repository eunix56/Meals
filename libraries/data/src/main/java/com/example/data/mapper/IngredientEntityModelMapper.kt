package com.example.data.mapper

import com.example.data.entities.IngredientEntity
import com.example.data.mapper.base.EntityModelMapper
import com.example.domain.model.Ingredient

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class IngredientEntityModelMapper: EntityModelMapper<IngredientEntity, Ingredient> {

    override fun mapFromEntity(entity: IngredientEntity): Ingredient {
        return Ingredient(
            entity.id,
            entity.ingredientName,
            entity.description,
            entity.type
        )
    }

    override fun mapToEntity(domain: Ingredient): IngredientEntity {
        return IngredientEntity(
            domain.id,
            domain.ingredientName,
            domain.description,
            domain.type
        )
    }
}