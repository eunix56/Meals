package com.eunice.remote.mapper

import com.eunice.data.entities.CategoryEntity
import com.eunice.remote.mapper.base.RemoteModelEntityMapper
import com.eunice.remote.model.CategoryModel

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class CategoryModelEntityMapper : RemoteModelEntityMapper<CategoryModel, CategoryEntity> {
    override fun mapFromModel(model: CategoryModel): CategoryEntity {
        return CategoryEntity(model.categoryId, model.categoryName,
            model.categoryImg, model.categoryDesc)
    }
}