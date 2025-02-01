package com.eunice.ingredients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Ingredient
import com.eunice.domain.model.Place
import com.eunice.domain.usecase.FetchIngredientsUseCase
import com.eunice.domain.usecase.FetchPlacesUseCase
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by {EUNICE BAKARE T.} on {3/9/23}
 * Email: {eunice@reach.africa}
 */

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    private val fetchIngredientsUseCase: FetchIngredientsUseCase,
    private val fetchPlacesUseCase: FetchPlacesUseCase
): ViewModel() {

    private val _ingredientsUIState = MutableStateFlow(IngredientsUIState(isLoading = true))
    val ingredientsUIState: StateFlow<IngredientsUIState> = _ingredientsUIState

    private val _placesUIState = MutableStateFlow(PlacesUIState(isLoading = true))
    val placesUIState: StateFlow<PlacesUIState> = _placesUIState


    init {
        viewModelScope.launch {
            fetchPlaces()
            fetchIngredients()
        }
    }

    private suspend fun fetchIngredients() {
        when (val ingredients =
            fetchIngredientsUseCase().first()) {
            is DataResult.Success -> {
                _ingredientsUIState.update {
                        uiState -> uiState.copy(isLoading = false,
                    ingredients = ingredients.data)
                }
            }

            is DataResult.Error -> _ingredientsUIState.update {
                    uiState -> uiState.copy(isLoading = false,
                errorMessage = getErrorResponse(ingredients.error)
            )
            }
            else -> _ingredientsUIState.update {
                    uiState -> uiState.copy(isLoading = false)
            }
        }
    }

    private suspend fun fetchPlaces() {
        when (val places =
            fetchPlacesUseCase().first()) {
            is DataResult.Success -> {
                _placesUIState.update {
                        uiState -> uiState.copy(isLoading = false,
                    places = places.data)
                }
            }

            is DataResult.Error -> _placesUIState.update {
                    uiState -> uiState.copy(isLoading = false,
                errorMessage = getErrorResponse(places.error)
            )
            }
            else -> _placesUIState.update {
                    uiState -> uiState.copy(isLoading = false)
            }
        }
    }

    data class PlacesUIState(
        val isLoading: Boolean = false,
        val places: List<Place> = emptyList(),
        val errorMessage: String? = null
    )

    data class IngredientsUIState(
        val isLoading: Boolean = false,
        val ingredients: List<Ingredient> = emptyList(),
        val errorMessage: String? = null
    )
}