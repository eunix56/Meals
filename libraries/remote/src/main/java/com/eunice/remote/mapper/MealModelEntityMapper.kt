package com.eunice.remote.mapper

import com.eunice.data.entities.MealDTO
import com.eunice.remote.mapper.base.RemoteModelEntityMapper
import com.eunice.remote.model.MealModel

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class MealModelEntityMapper:
    RemoteModelEntityMapper<MealModel, MealDTO> {

    override fun mapFromModel(model: MealModel): MealDTO {
        return MealDTO(
            model.id,
            model.mealName,
            model.mealImg,
            model.drink,
            model.mealCategory,
            model.mealArea,
            model.mealSteps,
            model.mealTags,
            model.mealVideo,
            model.ingredientOne,
            model.ingredientTwo,
            model.ingredientThree,
            model.ingredientFour,
            model.ingredientFive,
            model.ingredientSix,
            model.ingredientSeven,
            model.ingredientEight,
            model.ingredientNine,
            model.ingredientTen,
            model.ingredientEleven,
            model.ingredientTwelve,
            model.ingredientThirteen,
            model.ingredientFourteen,
            model.ingredientFifteen,
            model.ingredientSixteen,
            model.ingredientSeventeen,
            model.ingredientEighteen,
            model.ingredientNineteen,
            model.ingredientTwenty,
            model.measureOne,
            model.measureTwo,
            model.measureThree,
            model.measureFour,
            model.measureFive,
            model.measureSix,
            model.measureSeven,
            model.measureEight,
            model.measureNine,
            model.measureTen,
            model.measureEleven,
            model.measureTwelve,
            model.measureThirteen,
            model.measureFourteen,
            model.measureFifteen,
            model.measureSixteen,
            model.measureSeventeen,
            model.measureEighteen,
            model.measureNineteen,
            model.measureTwenty,
            model.mealSource,
            model.mealImgSource
        )
    }
}