package com.eunice.recipes

import androidx.lifecycle.ViewModel
import com.eunice.domain.handler.DataResult
import com.eunice.domain.model.Meal
import com.eunice.domain.usecase.AddMealToFavouritesUseCase
import com.eunice.domain.usecase.FetchMealByIdUseCase
import com.eunice.domain.usecase.RemoveMealFromFavouritesUseCase
import com.eunice.view.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val mealByIdUseCase: FetchMealByIdUseCase,
    private val addMealToFavouritesUseCase: AddMealToFavouritesUseCase,
    private val removeMealFromFavouritesUseCase: RemoveMealFromFavouritesUseCase
): ViewModel() {
    private val _mealUIState = MutableStateFlow(MealUIState(isLoading = true))
    val mealUIState: StateFlow<MealUIState> = _mealUIState

    private val _favouriteMealUIState = MutableStateFlow(FavouriteMealUIState())
    val favouriteMealUIState: StateFlow<FavouriteMealUIState> = _favouriteMealUIState
    
    suspend fun getMealById(id: String) {
        when (val mealById =
            mealByIdUseCase.invoke(id).first()) {
            is DataResult.Success -> {
                _mealUIState.update {
                        uiState -> uiState.copy(isLoading = false, meal = mealById.data)
                }
                _favouriteMealUIState.update {
                        uiState -> uiState.copy(isFavourite = mealById.data.isFavourite,
                    mealId = mealById.data.id)
                }
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

    suspend fun markMealAsFavourite(mealId: String) {
        when (val mealName = addMealToFavouritesUseCase(mealId).first()) {
            is DataResult.Error -> _favouriteMealUIState.update {
                    uiState -> uiState.copy(errorMessage = getErrorResponse(mealName.error)
            ) }
            is DataResult.Success -> _favouriteMealUIState.update {
                    uiState -> uiState.copy(isFavourite = true, favouriteMealName = mealName.data) }
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
    
    data class MealUIState(
        val isLoading: Boolean = false,
        val meal: Meal? = null,
        val isFavourite: Boolean = meal != null && meal.isFavourite,
        val errorMessage: String? = null
    )

    data class FavouriteMealUIState(
        val isFavourite: Boolean = false,
        val mealId: String? = null,
        val favouriteMealName: String? = null,
        val errorMessage: String? = null
    )
}

