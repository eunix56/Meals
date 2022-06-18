package com.eunice.data.mapper

import com.eunice.data.entities.CategoryEntity
import com.eunice.data.mapper.base.EntityModelMapper
import com.eunice.domain.model.Category

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