package com.example.data.mapper

import com.example.data.entities.CategoryEntity
import com.example.data.mapper.base.EntityModelMapper
import com.example.domain.model.Category

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class CategoryEntityModelMapper: EntityModelMapper<CategoryEntity, Category> {

    override fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(
            entity.categoryId,
            entity.categoryName,
            entity.categoryImg,
            entity.categoryDesc
        )
    }

    override fun mapToEntity(domain: Category): CategoryEntity {
       return CategoryEntity(
           domain.categoryId,
           domain.categoryName,
           domain.categoryImg,
           domain.categoryDesc
       )
    }
}