package com.eunice.recipes

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Category
import com.eunice.domain.model.Meal
import com.eunice.domain.usecase.FetchFullCategoriesUseCase
import com.eunice.domain.usecase.FetchRandomMealUseCase
import com.eunice.domain.usecase.FetchSearchedMealsUseCase
import com.eunice.utils.Prefs
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val fullCategoriesUseCase: FetchFullCategoriesUseCase,
    private val searchedMealsUseCase: FetchSearchedMealsUseCase,
    private val randomMealUseCase: FetchRandomMealUseCase,
    private val prefs: Prefs
) : ViewModel() {
    
    private val _mealUIState = MutableStateFlow(MealUIState(isLoading = true))
    val mealUIState: StateFlow<MealUIState> = _mealUIState
    
    private val _mealWithCategoriesUIState = MutableStateFlow(MealWithCategoryUIState(isLoading = true))
    val mealWithCategoriesUIState: StateFlow<MealWithCategoryUIState> = _mealWithCategoriesUIState
    
    suspend fun getSearchedMeals(searchQuery: String) {
        when (val searchedMeals =
            searchedMealsUseCase.invoke(searchQuery).first()) {
            is DataResult.Success -> _mealUIState.update {
                    uiState -> uiState.copy(isLoading = false, meals = searchedMeals.data)
            }
        
            is DataResult.Error -> _mealUIState.update {
                    uiState -> uiState.copy(isLoading = false,
                errorMessage = getErrorResponse(searchedMeals.error))
            }
            else -> _mealUIState.update {
                    uiState -> uiState.copy(isLoading = true)
            }
        }
    }
    
    private suspend fun getRandomMeal() {
        when(val randomMeal =
            randomMealUseCase.invoke().first()) {
        
            is DataResult.Success -> {
                prefs.saveRandomMeal(randomMeal.data)
                if (prefs.getFullCategories() == null) getCategories(randomMeal.data)
                else getCategoriesFromCache(prefs.getRandomMeal())
            }
            is DataResult.Error ->  _mealWithCategoriesUIState.update { uiState ->
                uiState.copy(
                    isLoading = false,
                    errorMessage = getErrorResponse(randomMeal.error)
                )
            }
        
            else ->  _mealWithCategoriesUIState.update {
                    uiState -> uiState.copy(isLoading = true)
            }
        }
    }
    
    suspend fun getRandomMealWithCategories() {
        if (!prefs.getIsFullDay()) {
            val randomMeal = prefs.getRandomMeal()
    
            if (randomMeal == null) getRandomMeal()
            else getCategories(randomMeal)
        } else {
            getRandomMeal()
        }
        
    }
    
    private fun getCategoriesFromCache(randomMeal: Meal?) {
        if (prefs.getFullCategories()?.isNotEmpty() == true) {
            _mealWithCategoriesUIState.update { uiState ->
                uiState.copy(
                    isLoading = false,
                    meal = randomMeal, categories = prefs.getFullCategories()!!
                )
            }
            return
        }
    }
    
    private suspend fun getCategories(randomMeal: Meal) {
        when (val categories =
            fullCategoriesUseCase.invoke().first()) {
            is DataResult.Success -> {
                prefs.saveFullCategories(categories.data)
                _mealWithCategoriesUIState.update {
                        uiState -> uiState.copy(isLoading = false,
                    meal = randomMeal, categories = categories.data)
                }
            }
            
            is DataResult.Error -> _mealWithCategoriesUIState.update {
                    uiState -> uiState.copy(isLoading = false,
                errorMessage = getErrorResponse(categories.error))
            }
            
            else -> _mealWithCategoriesUIState.update {
                    uiState -> uiState.copy(isLoading = true)
            }
        }
    }
    
    data class MealUIState(
        val isLoading: Boolean = false,
        val meals: List<Meal>? = emptyList(),
        val errorMessage: String? = null
    )
    
    data class MealWithCategoryUIState(
        val isLoading: Boolean = false,
        val meal: Meal? = null,
        val categories: List<Category> = emptyList(),
        val errorMessage: String? = null
    )
}