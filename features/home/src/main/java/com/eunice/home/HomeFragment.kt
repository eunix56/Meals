package com.eunice.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.eunice.domain.model.Category
import com.eunice.home.databinding.HomeFragmentBinding
import com.eunice.navigation.NavigationFlow
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

        lifecycleScope.launchWhenStarted {
            viewModel.categoryUIState.collect {
                homeFragmentBinding.searchBar.visibility = if (it.isLoading) View.GONE else View.VISIBLE

                if (it.categories.isNotEmpty()) {
                    val categoryAdapter = MealCategoryAdapter(it.categories, this@HomeFragment)
                    homeFragmentBinding.rvCategory.adapter = categoryAdapter
                }

                if (it.errorMessage?.isNotBlank() == true) {
                    homeFragmentBinding.searchBar.isEnabled = false
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.categoryMealsUIState.collect {
                homeFragmentBinding.lavLoader.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                homeFragmentBinding.tvCategoryHeader.visibility = if (it.isLoading) View.GONE else View.VISIBLE

                homeFragmentBinding.tvCategoryHeader.text = it.category

                if (it.meals.isNotEmpty()) {
                    val categoryMealsAdapter = CategoryMealsAdapter(it.meals) { id ->
                        navigator.navigateToDetailsFlow(NavigationFlow.RecipeDetailsFlow, id)
                    }
                    homeFragmentBinding.rvCategoryMeals.adapter = categoryMealsAdapter
                }

                if (it.errorMessage?.isNotBlank() == true) {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
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