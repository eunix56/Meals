package com.example.data.mapper

import com.example.data.entities.MealEntity
import com.example.data.mapper.base.EntityModelMapper
import com.example.domain.model.Meal

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class MealEntityModelMapper()
    : EntityModelMapper<MealEntity, Meal> {

    override fun mapFromEntity(entity: MealEntity): Meal {
        return Meal(
            entity.id,
            entity.mealName,
            entity.drink,
            entity.mealCategory,
            entity.mealArea,
            entity.mealSteps,
            entity.mealImg,
            entity.mealTags,
            entity.mealVideo,
            entity.ingredientOne,
            entity.ingredientTwo,
            entity.ingredientThree,
            entity.ingredientFour,
            entity.ingredientFive,
            entity.ingredientSix,
            entity.ingredientSeven,
            entity.ingredientEight,
            entity.ingredientNine,
            entity.ingredientTen,
            entity.ingredientEleven,
            entity.ingredientTwelve,
            entity.ingredientThirteen,
            entity.ingredientFourteen,
            entity.ingredientFifteen,
            entity.ingredientSixteen,
            entity.ingredientSeventeen,
            entity.ingredientEighteen,
            entity.ingredientNineteen,
            entity.ingredientTwenty,
            entity.measureOne,
            entity.measureTwo,
            entity.measureThree,
            entity.measureFour,
            entity.measureFive,
            entity.measureSix,
            entity.measureSeven,
            entity.measureEight,
            entity.measureNine,
            entity.measureTen,
            entity.measureEleven,
            entity.measureTwelve,
            entity.measureThirteen,
            entity.measureFourteen,
            entity.measureFifteen,
            entity.measureSixteen,
            entity.measureSeventeen,
            entity.measureEighteen,
            entity.measureNineteen,
            entity.measureTwenty,
            entity.mealSource,
            entity.mealImgSource
        )
    }

    override fun mapToEntity(domain: Meal): MealEntity {
        return MealEntity(
            domain.id,
            domain.mealName,
            domain.drink,
            domain.mealCategory,
            domain.mealArea,
            domain.mealSteps,
            domain.mealImg,
            domain.mealTags,
            domain.mealVideo,
            domain.ingredientOne,
            domain.ingredientTwo,
            domain.ingredientThree,
            domain.ingredientFour,
            domain.ingredientFive,
            domain.ingredientSix,
            domain.ingredientSeven,
            domain.ingredientEight,
            domain.ingredientNine,
            domain.ingredientTen,
            domain.ingredientEleven,
            domain.ingredientTwelve,
            domain.ingredientThirteen,
            domain.ingredientFourteen,
            domain.ingredientFifteen,
            domain.ingredientSixteen,
            domain.ingredientSeventeen,
            domain.ingredientEighteen,
            domain.ingredientNineteen,
            domain.ingredientTwenty,
            domain.measureOne,
            domain.measureTwo,
            domain.measureThree,
            domain.measureFour,
            domain.measureFive,
            domain.measureSix,
            domain.measureSeven,
            domain.measureEight,
            domain.measureNine,
            domain.measureTen,
            domain.measureEleven,
            domain.measureTwelve,
            domain.measureThirteen,
            domain.measureFourteen,
            domain.measureFifteen,
            domain.measureSixteen,
            domain.measureSeventeen,
            domain.measureEighteen,
            domain.measureNineteen,
            domain.measureTwenty,
            domain.mealSource,
            domain.mealImgSource
        )
    }
}