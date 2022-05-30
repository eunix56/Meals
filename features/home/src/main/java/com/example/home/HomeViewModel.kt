package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.handler.DataResult
import com.example.domain.model.Category
import com.example.domain.model.Meal
import com.example.domain.usecase.FetchCategoriesUseCase
import com.example.domain.usecase.FetchCategoryMealsUseCase
import com.example.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryMealsUseCase: FetchCategoryMealsUseCase,
    private val categoriesUseCase: FetchCategoriesUseCase
) : ViewModel() {
    
    private val _categoryUIState = MutableStateFlow(CategoryUIState(isLoading = true))
    val categoryUIState: StateFlow<CategoryUIState> = _categoryUIState

    private val _categoryMealsUIState = MutableStateFlow(CategoryMealsUIState(isLoading = true))
    val categoryMealsUIState: StateFlow<CategoryMealsUIState> = _categoryMealsUIState

    init {
        viewModelScope.launch {

            when(val categories: DataResult<List<Category>>
            = categoriesUseCase.invoke().first()) {

                is DataResult.Success -> {
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
    }

    suspend fun selectCategory(categoryName: String) {
        _categoryMealsUIState.update {
                uiState -> uiState.copy(category = categoryName)
        }

        when (val categoryMeals =
            categoryMealsUseCase.invoke(categoryName).first()) {
            is DataResult.Success -> _categoryMealsUIState.update {
                    uiState -> uiState.copy(isLoading = false, meals = categoryMeals.data)
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

