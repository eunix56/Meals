package com.example.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.FetchCategoriesUseCase
import com.example.domain.usecase.FetchCategoryMealsUseCase

/**
 * Created by EUNICE BAKARE T. on 29/04/2022
 * Email: eunice@reach.africa
 */
class HomeViewModelFactory(private val categoryMealsUseCase: FetchCategoryMealsUseCase,
                           private val categoriesUseCase: FetchCategoriesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(categoryMealsUseCase, categoriesUseCase) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}