package com.eunice.navigation

import androidx.navigation.NavController
import javax.inject.Inject


/**
 * Created by {EUNICE BAKARE T.} on {5/29/22}
 * Email: {eunice@reach.africa}
 */

class Navigator @Inject constructor() {
    lateinit var navController: NavController
    
    fun navigateToGlobalFlow(globalNavigationFlow: GlobalNavigationFlow) = when(globalNavigationFlow) {
        GlobalNavigationFlow.HomeFlow -> navController.navigate(NavGraphDirections.actionGlobalHomeFlow())
        GlobalNavigationFlow.RecipeFlow -> navController.navigate(NavGraphDirections.actionGlobalRecipesFlow(null))
        GlobalNavigationFlow.IngredientFlow -> navController.navigate(NavGraphDirections.actionGlobalIngredientsFlow())
    }
    
    fun navigateToSearchResultsFlow(searchQuery: String) =
        navController.navigate(NavGraphDirections.actionGlobalRecipesFlow(searchQuery))
    
    fun navigateToRecipesDetailsFlow(id: String) =
            navController.navigate(NavGraphDirections.actionRecipeDetailsFlow(id))
    
}