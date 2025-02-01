package com.eunice.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.eunice.navigation.Navigator
import com.eunice.recipes.databinding.RecipesFragmentBinding
import com.eunice.utils.Prefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class RecipesFragment : Fragment() {
    
    @Inject
    lateinit var navigator: Navigator
    
    private lateinit var recipesFragmentBinding: RecipesFragmentBinding
    private val recipesFragmentArgs by navArgs<RecipesFragmentArgs>()
    private val viewModel by viewModels<RecipesViewModel>()

    //The idea is to use this for the search activity and the home activity for the categories

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        
        recipesFragmentBinding = RecipesFragmentBinding.inflate(inflater)
        return recipesFragmentBinding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    
        lifecycleScope.launchWhenStarted {
            if (recipesFragmentArgs.searchQuery.isNullOrEmpty())
                viewModel.getRandomMealWithCategories()
            else
                viewModel.getSearchedMeals(recipesFragmentArgs.searchQuery!!)
        }
        
        lifecycleScope.launchWhenStarted {
            if (recipesFragmentArgs.searchQuery.isNullOrEmpty())
                viewModel.mealWithCategoriesUIState.collect {
                    setupRandomMealWithCategories(it)
                }
            else
                viewModel.mealUIState.collect {
                    setupSearchMeals(it)
                }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.favouriteMealUIState.collectLatest {
                if (it.favouriteMealName?.isEmpty() == true) return@collectLatest

                if (it.favouriteMealName == null) {
                    Toast.makeText(
                        context, getString(R.string.unable_to_add_to_fav, it.errorMessage),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context, getString(R.string.meal_added_to_fav, it.favouriteMealName),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        
    }
    
    private fun setupSearchMeals(mealUIState: RecipesViewModel.MealUIState) {
        if (!mealUIState.meals.isNullOrEmpty()) {
            val adapter = SearchedMealsAdapter(mealUIState.meals) {
                lifecycleScope.launchWhenStarted {
                    if (!it.isFavourite) {
                        viewModel.markMealAsFavourite(it.id)
                    }
                }
            }

            recipesFragmentBinding.rvRecipesData.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recipesFragmentBinding.rvRecipesData.adapter = adapter
        }
        
        if (mealUIState.errorMessage?.isNotBlank() == true) {
            Toast.makeText(context, mealUIState.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
    
    private fun setupRandomMealWithCategories(mealWithCategoryUIState
                                              : RecipesViewModel.MealWithCategoryUIState) {
        
        if (mealWithCategoryUIState.meal != null
            && mealWithCategoryUIState.categories.isNotEmpty()
        ) {
            val adapter = RecipesDataAdapter(mealWithCategoryUIState.meal,
                mealWithCategoryUIState.categories, {

                }, {
                    navigator.navigateToRecipesDetailsFlow(it.id)
                }) {
                lifecycleScope.launchWhenStarted {
                    if (!it.isFavourite) {
                        viewModel.markMealAsFavourite(it.id)
                    }
                }
            }

            recipesFragmentBinding.rvRecipesData.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recipesFragmentBinding.rvRecipesData.adapter = adapter
        }
        
        if (mealWithCategoryUIState.errorMessage?.isNotBlank() == true) {
            Toast.makeText(context, mealWithCategoryUIState.errorMessage, Toast.LENGTH_LONG).show()
        }
    
    }
    
    companion object {
        fun newInstance() = RecipesFragment()
    }

}