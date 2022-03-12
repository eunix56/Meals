package com.example.remote.mapper

import com.example.data.entities.CategoryEntity
import com.example.remote.mapper.base.RemoteModelEntityMapper
import com.example.remote.model.CategoryModel

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class CategoryModelEntityMapper(): RemoteModelEntityMapper<CategoryModel, CategoryEntity> {
    override fun mapFromModel(model: CategoryModel): CategoryEntity {
        return CategoryEntity(model.categoryName)
    }
}