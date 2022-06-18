package com.eunice.recipes

import androidx.annotation.IntDef


/**
 * Created by {EUNICE BAKARE T.} on {5/26/22}
 * Email: {eunice@reach.africa}
 */
@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@IntDef(TITLE, INGREDIENTS, INSTRUCTIONS)
annotation class MealViewType
    const val TITLE = 0
    const val INGREDIENTS = 1
    const val INSTRUCTIONS = 2