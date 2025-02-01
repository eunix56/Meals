package com.eunice.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.usecase.FetchFavouriteMealsUseCase
import com.eunice.domain.usecase.RemoveMealFromFavouritesUseCase
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val removeMealFromFavouritesUseCase: RemoveMealFromFavouritesUseCase,
    private val favouriteMealsUseCase: FetchFavouriteMealsUseCase
) : ViewModel() {

    private val _mealsUIState = MutableStateFlow(MealsUIState(isLoading = true))
    val mealsUIState: StateFlow<MealsUIState> = _mealsUIState

    private val _favouriteMealUIState = MutableStateFlow(FavouriteMealUIState())
    val favouriteMealUIState: StateFlow<FavouriteMealUIState> = _favouriteMealUIState

    fun getFavouriteMeals() {
        viewModelScope.launch {
            when (val favouriteMeals =
                favouriteMealsUseCase.invoke().first()) {
                is DataResult.Success -> _mealsUIState.update {
                        uiState -> uiState.copy(isLoading = false, meals = favouriteMeals.data)
                }

                is DataResult.Error -> _mealsUIState.update {
                        uiState -> uiState.copy(isLoading = false,
                    errorMessage = getErrorResponse(favouriteMeals.error)
                )
                }
                else -> _mealsUIState.update {
                        uiState -> uiState.copy(isLoading = true)
                }
            }
        }
    }

    suspend fun removeMealFromFavourite(mealId: String) {
        when (val mealName = removeMealFromFavouritesUseCase(mealId).first()) {
            is DataResult.Success -> {
                _favouriteMealUIState.update {
                        uiState -> uiState.copy(isFavourite = false,
                    favouriteMealName = mealName.data)
                }
            }

            is DataResult.Error -> _favouriteMealUIState.update {
                    uiState -> uiState.copy(errorMessage = getErrorResponse(mealName.error)
            )
            }
        }
    }


    data class FavouriteMealUIState(
        val isFavourite: Boolean = false,
        val mealId: String? = null,
        val favouriteMealName: String? = null,
        val errorMessage: String? = null
    )

    data class MealsUIState(
        val isLoading: Boolean = false,
        val meals: List<Meal>? = emptyList(),
        val favouriteSuccess: Boolean = false,
        val errorMessage: String? = null
    )
}