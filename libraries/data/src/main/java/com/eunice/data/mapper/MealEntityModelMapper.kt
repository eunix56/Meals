package com.eunice.data.mapper

import com.eunice.data.db.entity.MealEntity
import com.eunice.data.entities.MealDTO
import com.eunice.data.mapper.base.EntityModelMapper
import com.eunice.domain.model.IngredientData
import com.eunice.domain.model.Instruction
import com.eunice.domain.model.Meal

/**
 * Created by EUNICE BAKARE T. on 11/03/2022
 * Email: eunice@reach.africa
 */
class MealEntityModelMapper
    : EntityModelMapper<MealEntity, Meal> {

    override fun mapFromEntity(entity: MealEntity): Meal {
        return Meal(
            entity.id,
            entity.mealName,
            entity.mealImg,
            entity.drink,
            entity.mealCategory ?: "Meal",
            entity.mealArea ?: "World",
            entity.mealSteps,
            entity.mealTags,
            entity.mealVideo,
            entity.ingredients,
            entity.mealSource,
            entity.mealImgSource
        )
    }

    override fun mapToEntity(domain: Meal): MealEntity {
        return MealEntity(
            domain.id,
            domain.mealName,
            domain.mealImg,
            domain.drink,
            domain.mealCategory,
            domain.mealArea,
            domain.mealSteps,
            domain.mealTags,
            domain.mealVideo,
            domain.ingredients,
            domain.mealSource,
            domain.mealImgSource
        )
    }

    fun mapFromDTO(mealDTO: MealDTO): MealEntity {
        return MealEntity(
            mealDTO.id, mealDTO.mealName, mealDTO.mealImg, mealDTO.drink, mealDTO.mealCategory,
            mealDTO.mealArea, mealDTO.mealSteps, mealDTO.mealTags, mealDTO.mealVideo,
            convertMealToIngredients(mealDTO), mealDTO.mealSource, mealDTO.mealImgSource
        )
    }

    fun mapToDomain(mealDTO: MealDTO): Meal {
        return Meal(
            mealDTO.id, mealDTO.mealName, mealDTO.mealImg, mealDTO.drink, mealDTO.mealCategory ?: "Meal",
            mealDTO.mealArea ?: "World", mealDTO.mealSteps, mealDTO.mealTags, mealDTO.mealVideo,
            convertMealToIngredients(mealDTO), mealDTO.mealSource, mealDTO.mealImgSource
        )
    }

    private fun convertMealToIngredients(meal: MealDTO): List<IngredientData> {
        val ingredientsData = ArrayList<IngredientData>()

        ingredientsData.add(IngredientData(meal.ingredientOne, meal.measureOne))
        ingredientsData.add(IngredientData(meal.ingredientTwo, meal.measureTwo))
        ingredientsData.add(IngredientData(meal.ingredientThree, meal.measureThree))
        ingredientsData.add(IngredientData(meal.ingredientFour, meal.measureFour))
        ingredientsData.add(IngredientData(meal.ingredientFive, meal.measureFive))
        ingredientsData.add(IngredientData(meal.ingredientSix, meal.measureSix))
        ingredientsData.add(IngredientData(meal.ingredientSeven, meal.measureSeven))
        ingredientsData.add(IngredientData(meal.ingredientEight, meal.measureEight))
        ingredientsData.add(IngredientData(meal.ingredientNine, meal.measureNine))
        ingredientsData.add(IngredientData(meal.ingredientTen, meal.measureTen))
        ingredientsData.add(IngredientData(meal.ingredientEleven, meal.measureEleven))
        ingredientsData.add(IngredientData(meal.ingredientTwelve, meal.measureTwelve))
        ingredientsData.add(IngredientData(meal.ingredientThirteen, meal.measureThirteen))
        ingredientsData.add(IngredientData(meal.ingredientFourteen, meal.measureFourteen))
        ingredientsData.add(IngredientData(meal.ingredientFifteen, meal.measureFifteen))
        ingredientsData.add(IngredientData(meal.ingredientSixteen, meal.measureSixteen))
        ingredientsData.add(IngredientData(meal.ingredientSeventeen, meal.measureSeventeen))
        ingredientsData.add(IngredientData(meal.ingredientEighteen, meal.measureEighteen))
        ingredientsData.add(IngredientData(meal.ingredientNineteen, meal.measureNineteen))
        ingredientsData.add(IngredientData(meal.ingredientTwenty, meal.measureTwenty))

        val mainIngredients = ingredientsData.filter { data ->
            !data.ingredient.isNullOrEmpty()
        }.distinct()

        return mainIngredients
    }
}