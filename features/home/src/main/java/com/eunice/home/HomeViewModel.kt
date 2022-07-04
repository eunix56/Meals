package com.eunice.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Category
import com.eunice.domain.model.CategoryAndMeals
import com.eunice.domain.model.Meal
import com.eunice.domain.usecase.FetchCategoriesUseCase
import com.eunice.domain.usecase.FetchCategoryMealsUseCase
import com.eunice.utils.Prefs
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryMealsUseCase: FetchCategoryMealsUseCase,
    private val categoriesUseCase: FetchCategoriesUseCase,
    private val prefs: Prefs
) : ViewModel() {
    
    private val _categoryUIState = MutableStateFlow(CategoryUIState(isLoading = true))
    val categoryUIState: StateFlow<CategoryUIState> = _categoryUIState

    private val _categoryMealsUIState = MutableStateFlow(CategoryMealsUIState(isLoading = true))
    val categoryMealsUIState: StateFlow<CategoryMealsUIState> = _categoryMealsUIState

    init {
        viewModelScope.launch {
            getCategoriesFromCache()
        }
    }

    private suspend fun selectCategoryRemote(categoryName: String) {
        _categoryMealsUIState.update {
                uiState -> uiState.copy(category = categoryName)
        }

        when (val categoryMeals =
            categoryMealsUseCase.invoke(categoryName).first()) {
            is DataResult.Success -> {
                prefs.saveCategoryMeals(categoryMeals.data)
                _categoryMealsUIState.update {
                        uiState -> uiState.copy(isLoading = false, meals = categoryMeals.data)
                }
            }

            is DataResult.Error -> _categoryMealsUIState.update {
                    uiState -> uiState.copy(isLoading = false,
                errorMessage = getErrorResponse(categoryMeals.error))
            }
            else -> _categoryMealsUIState.update {
                    uiState -> uiState.copy(isLoading = true)
            }
        }
    }
    
    private suspend fun getCategories() {
        when(val categories: DataResult<List<Category>>
                = categoriesUseCase.invoke().first()) {
        
            is DataResult.Success -> {
                prefs.saveCategories(categories.data)
                _categoryUIState.update { uiState ->
                    uiState.copy(isLoading = false,
                        categories = categories.data)
                }
                selectCategory(categories.data.first().categoryName)
            }
            is DataResult.Error -> _categoryUIState.update {
                    uiState -> uiState.copy(isLoading = false,
                errorMessage = getErrorResponse(categories.error))
            }
            else -> _categoryUIState.update {
                    uiState -> uiState.copy(isLoading = true)
            }
        }
    }
    
    suspend fun selectCategory(categoryName: String) {
        val categoryMeals = prefs.getCategoryMeals()
        
        if (categoryMeals.isNullOrEmpty()) selectCategoryRemote(categoryName)
        else getMealsFromCategory(categoryName, categoryMeals)
    }
    
    private suspend fun getMealsFromCategory(categoryName: String,
                                             categoryMeals: List<Meal>) {
        val meals = ArrayList<Meal>()
        for (meal in categoryMeals) {
            if (meal.mealCategory.equals(categoryName)
                || meal.mealName.contains(categoryName, true))
                meals.add(meal)
        }
        
        if (meals.isNullOrEmpty())
            selectCategoryRemote(categoryName)
        else
            _categoryMealsUIState.update {
                    uiState -> uiState.copy(isLoading = false, meals = meals)
            }
    }
    
    private suspend fun getCategoriesFromCache() {
        if (prefs.getCategories()?.isNotEmpty() == true) {
            _categoryUIState.update { uiState ->
                uiState.copy(isLoading = false,
                    categories = prefs.getCategories()!!)
            }
            selectCategory(prefs.getCategories()!!.first().categoryName)
        } else getCategories()
    }


    data class CategoryUIState(
        val isLoading: Boolean = false,
        val categories: List<Category> = emptyList(),
        val errorMessage: String? = null
    )

    data class CategoryMealsUIState(
        val isLoading: Boolean = false,
        val category: String? = null,
        val meals: List<Meal> = emptyList(),
        val errorMessage: String? = null
    )


}

