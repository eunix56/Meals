package com.eunice.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.eunice.domain.model.Category
import com.eunice.home.databinding.HomeFragmentBinding
import com.eunice.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), MealCategory {
    private lateinit var homeFragmentBinding: HomeFragmentBinding
    
    @Inject
    lateinit var navigator: Navigator

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
        

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
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

        setupSearchUI()
        lifecycleScope.launchWhenStarted {
            viewModel.categoryUIState.collect {
                setupCategory(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.categoryMealsUIState.collect {
                setupCategoryMeals(it)
            }
        }
    }
    
    private fun setupCategory(uiState
                              : HomeViewModel.CategoryUIState) {
        homeFragmentBinding.searchBar.visibility = if (uiState.isLoading)
            View.GONE else View.VISIBLE
    
        if (uiState.categories.isNotEmpty()) {
            val categoryAdapter = MealCategoryAdapter(uiState.categories,
                this@HomeFragment)
            
            homeFragmentBinding.rvCategory.adapter = categoryAdapter
        }
    
        if (uiState.errorMessage?.isNotBlank() == true) {
            homeFragmentBinding.searchBar.isEnabled = false
            Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    
    private fun setupCategoryMeals(mealsUIState
                                   : HomeViewModel.CategoryMealsUIState) {
        homeFragmentBinding.lavLoader.visibility = if (mealsUIState.isLoading)
            View.VISIBLE else View.GONE
        
        homeFragmentBinding.tvCategoryHeader.visibility = if (mealsUIState.isLoading)
            View.GONE else View.VISIBLE
    
        homeFragmentBinding.tvCategoryHeader.text = mealsUIState.category
    
        if (mealsUIState.meals.isNotEmpty()) {
            val categoryMealsAdapter = CategoryMealsAdapter(mealsUIState.meals) { id ->
                navigator.navigateToRecipesDetailsFlow(id)
            }
            homeFragmentBinding.rvCategoryMeals.adapter = categoryMealsAdapter
        }
    
        if (mealsUIState.errorMessage?.isNotBlank() == true) {
            Toast.makeText(context, mealsUIState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    
    private fun setupSearchUI() {
        homeFragmentBinding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return if (p0.isNullOrEmpty() || p0.isNullOrBlank())
                    false
                else {
                    navigator.navigateToSearchResultsFlow(p0.trim())
                    true
                }
            }
    
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
    
        })
    }

    override fun selectCategory(category: Category) {
        lifecycleScope.launchWhenStarted {
            viewModel.selectCategory(category.categoryName)
        }
    }

}