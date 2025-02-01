package com.eunice.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.eunice.domain.model.Category
import com.eunice.domain.model.CategoryAndMeals
import com.eunice.domain.model.Meal
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


/**
 * Created by {EUNICE BAKARE T.} on {6/27/22}
 * Email: {eunice@reach.africa}
 */

private const val PREFS: String = "prefs"
private const val CURRENT_DATE_TIME = "current_date_time"
private const val RANDOM_MEAL = "random_meal"
private const val CATEGORIES = "categories"
private const val CATEGORY_MEALS = "category_meals"
private const val CATEGORIES_AND_MEALS = "categories_and_meals"
private const val FULL_CATEGORIES = "full_categories"

class Prefs @Inject constructor(context: Context, val moshi: Moshi) {
    
    val sharedPrefs = context.getSharedPreferences(PREFS, MODE_PRIVATE)
    
    fun saveCurrentDateTime(currentTime: Long) {
        sharedPrefs.apply {
            edit().putLong(CURRENT_DATE_TIME, currentTime).apply()
        }
    }
    
    fun getIsFullDay(): Boolean {
        val formerTime = sharedPrefs.getLong(CURRENT_DATE_TIME, 0L)
        val currentTime = Date().time
        
        return currentTime.minus(formerTime) >= TimeUnit.HOURS.toMillis(24)
    }
    
    fun saveRandomMeal(meal: Meal) {
        sharedPrefs.apply {
            edit().putString(RANDOM_MEAL, convertJsonToString(meal)).apply()
        }
        saveCurrentDateTime(Date().time)
    }
    
    fun getRandomMeal(): Meal? {
        val meal = sharedPrefs.getString(RANDOM_MEAL, "")
        if (meal.isNullOrEmpty())
            return null
        return convertStringToJson(meal)
    }
    
    fun saveCategoryMeals(meals: List<Meal>) {
        sharedPrefs.apply {
            edit().putString(CATEGORY_MEALS, convertListJsonToString(meals)).apply()
        }
    }
    
    fun getCategoryMeals(): List<Meal>? {
        val meals = sharedPrefs.getString(CATEGORY_MEALS, "")
    
        if (meals.isNullOrEmpty())
            return emptyList()
        return convertListStringToJson(meals)
    }
    
    fun saveCategoriesAndMeals(categories: List<CategoryAndMeals>) {
        sharedPrefs.apply {
            edit().putString(CATEGORIES_AND_MEALS, convertJsonToString(categories)).apply()
        }
    }
    
    fun getCategoriesAndMeals(): List<CategoryAndMeals>? {
        val categories = sharedPrefs.getString(CATEGORIES_AND_MEALS, "")
        if (categories.isNullOrEmpty())
            return emptyList()
        return convertStringToJson(categories)
    }
    
    fun saveCategories(categories: List<String>) {
        sharedPrefs.apply {
            edit().putString(CATEGORIES, convertListJsonToString(categories)).apply()
        }
    }
    
    fun getCategories(): List<String>? {
        val categories = sharedPrefs.getString(CATEGORIES, "")
        if (categories.isNullOrEmpty())
            return emptyList()
        return convertListStringToJson(categories)
    }
    
    fun saveFullCategories(categories: List<Category>) {
        sharedPrefs.apply {
            edit().putString(FULL_CATEGORIES, convertListJsonToString(categories)).apply()
        }
    }
    
    fun getFullCategories(): List<Category>? {
        val categories = sharedPrefs.getString(FULL_CATEGORIES, "")
        if (categories.isNullOrEmpty())
            return emptyList()
        return convertListStringToJson(categories)
    }
    
    inline fun<reified T> convertJsonToString(meal: T): String {
        val jsonAdapter = moshi.adapter(T::class.java)
        
        return jsonAdapter.toJson(meal)
    }
    
    inline fun<reified T> convertStringToJson(meal: String): T? {
        val jsonAdapter = moshi.adapter(T::class.java)
        
        return jsonAdapter.fromJson(meal)
    }
    
    private inline fun<reified T> convertListJsonToString(meal: List<T>): String {
        val type = Types.newParameterizedType(List::class.java, T::class.java)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(type)
        return jsonAdapter.toJson(meal)
    }
    
    private inline fun<reified T> convertListStringToJson(meal: String): List<T>? {
        val type = Types.newParameterizedType(List::class.java, T::class.java)
        val jsonAdapter: JsonAdapter<List<T>> = moshi.adapter(type)
        return jsonAdapter.fromJson(meal)
    }
}