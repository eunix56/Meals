package com.eunice.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.usecase.FetchIngredientMealsUseCase
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by {EUNICE BAKARE T.} on {3/23/23}
 * Email: {eunice@reach.africa}
 */

@HiltViewModel
class IngredientDetailViewModel @Inject constructor(
    private val fetchIngredientMealsUseCase: FetchIngredientMealsUseCase
): ViewModel() {

    private val _ingredientMealsUIState = MutableStateFlow(IngredientMealsUIState(isLoading = true))
    val ingredientMealsUIState: StateFlow<IngredientMealsUIState> = _ingredientMealsUIState


    fun fetchIngredientMeals(ingredientName: String) {
        viewModelScope.launch {
            when (val meals =
                fetchIngredientMealsUseCase
                    .invoke(ingredientName).first()) {
                is DataResult.Success -> {
                    _ingredientMealsUIState.update {
                            uiState -> uiState.copy(isLoading = false,
                        meals = meals.data)
                    }
                }

                is DataResult.Error -> _ingredientMealsUIState.update {
                        uiState -> uiState.copy(isLoading = false,
                    errorMessage = getErrorResponse(meals.error)
                )
                }
                else -> _ingredientMealsUIState.update {
                        uiState -> uiState.copy(isLoading = false)
                }
            }
        }
    }

    data class IngredientMealsUIState(
        val isLoading: Boolean = false,
        val meals: List<Meal> = emptyList(),
        val errorMessage: String? = null
    )
}