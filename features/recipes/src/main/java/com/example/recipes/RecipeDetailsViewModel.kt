package com.example.recipes

import androidx.lifecycle.ViewModel
import com.example.domain.handler.DataResult
import com.example.domain.model.Meal
import com.example.domain.usecase.FetchMealByIdUseCase
import com.example.view.getErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(
    private val mealByIdUseCase: FetchMealByIdUseCase
): ViewModel() {
    private val _mealUIState = MutableStateFlow(MealUIState(isLoading = true))
    val mealUIState: StateFlow<MealUIState> = _mealUIState
    
    
    suspend fun getMealById(id: String) {
        when (val mealById =
            mealByIdUseCase.invoke(id).first()) {
            is DataResult.Success -> _mealUIState.update {
                    uiState -> uiState.copy(isLoading = false, meal = mealById.data)
            }
        
            is DataResult.Error -> _mealUIState.update {
                    uiState -> uiState.copy(isLoading = false,
                errorMessage = getErrorResponse(mealById.error)
            )
            }
            else -> _mealUIState.update {
                    uiState -> uiState.copy(isLoading = true)
            }
        }
    }
    
    data class MealUIState(
        val isLoading: Boolean = false,
        val meal: Meal? = null,
        val errorMessage: String? = null
    )
}

