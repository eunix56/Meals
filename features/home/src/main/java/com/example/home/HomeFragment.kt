package com.example.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.home.databinding.HomeFragmentBinding

import androidx.lifecycle.lifecycleScope
import com.example.domain.model.Category
import com.example.domain.usecase.FetchCategoryMealsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class HomeFragment : Fragment(), MealCategory {
    private lateinit var homeFragmentBinding: HomeFragmentBinding


    companion object {
        fun newInstance() = HomeFragment()
    }

    //Beef icon by Icons8
    //Breakfast icon by Icons8
    //Chicken icon by Icons8
    //Seafood icon by Icons8
    //Food Bar icon by Icons8
    //Tapas icon by Icons8
    //Vegan icon by Icons8
    //Vegan Food icon by Icons8
    //https://uxwing.com/
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        viewModel = ViewModelProvider(this,)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        lifecycleScope.launchWhenStarted {
            viewModel.categoryUIState.collect {
                homeFragmentBinding.searchBar.visibility = if (it.isLoading) View.GONE else View.VISIBLE

                if (it.categories.isNotEmpty()) {
                    val categoryAdapter = MealCategoryAdapter(it.categories, this@HomeFragment)
                    homeFragmentBinding.rvCategory.adapter = categoryAdapter
                }

                if (it.errorMessage?.isNotBlank() == true) {
                    homeFragmentBinding.searchBar.visibility = View.GONE

                }
            }


            viewModel.categoryMealsUIState.collect {
                homeFragmentBinding.lavLoader.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                homeFragmentBinding.tvCategoryHeader.visibility = if (it.isLoading) View.GONE else View.VISIBLE

                homeFragmentBinding.tvCategoryHeader.text = it.category

                if (it.meals.isNotEmpty()) {
                    val categoryMealsAdapter = CategoryMealsAdapter(it.meals)
                    homeFragmentBinding.rvCategoryMeals.adapter = categoryMealsAdapter
                }

                if (it.errorMessage?.isNotBlank() == true) {
                    homeFragmentBinding.tvCategoryHeader.visibility = View.GONE

                }
            }
        }
    }

    override fun selectCategory(category: Category) {
        lifecycleScope.launchWhenStarted {
            viewModel.selectCategory(category.categoryName)
        }
    }

}