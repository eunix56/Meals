package com.eunice.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.usecase.FetchPlacesMealsUseCase
import com.eunice.domain.usecase.FetchPlacesUseCase
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by {EUNICE BAKARE T.} on {3/25/23}
 * Email: {eunice@reach.africa}
 */
@HiltViewModel
class AreaDetailViewModel @Inject constructor(
    private val fetchPlacesMealsUseCase: FetchPlacesMealsUseCase,
    private val fetchPlacesUseCase: FetchPlacesUseCase
): ViewModel() {

    private val _areaMealsUIState = MutableStateFlow(AreaMealsUIState(isLoading = true))
    val areaMealsUIState: StateFlow<AreaMealsUIState> = _areaMealsUIState


    fun fetchAreaDetailMeals(areaName: String) {
        viewModelScope.launch {
            when (val meals =
                fetchPlacesMealsUseCase
                    .invoke(areaName).first()) {
                is DataResult.Success -> {
                    _areaMealsUIState.update {
                            uiState -> uiState.copy(isLoading = false,
                        meals = meals.data)
                    }
                }

                is DataResult.Error -> _areaMealsUIState.update {
                        uiState -> uiState.copy(isLoading = false,
                    errorMessage = getErrorResponse(meals.error)
                )
                }
                else -> _areaMealsUIState.update {
                        uiState -> uiState.copy(isLoading = false)
                }
            }
        }
    }

    data class AreaMealsUIState(
        val isLoading: Boolean = false,
        val meals: List<Meal> = emptyList(),
        val errorMessage: String? = null
    )
}