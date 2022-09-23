package com.example.domain.model

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
data class Category(
    val categoryId: String?,
    val categoryName: String,
    val categoryImg: String?,
    val categoryDesc: String?,
    var isSelected: Boolean = false
)
