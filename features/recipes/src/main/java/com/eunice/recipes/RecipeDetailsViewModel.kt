package com.eunice.recipes

import androidx.lifecycle.ViewModel
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.usecase.FetchMealByIdUseCase
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
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

