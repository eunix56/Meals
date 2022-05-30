package com.example.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import javax.inject.Inject


/**
 * Created by {EUNICE BAKARE T.} on {5/29/22}
 * Email: {eunice@reach.africa}
 */

class Navigator @Inject constructor() {
    lateinit var navController: NavController
    
    fun navigateToGlobalFlow(globalNavigationFlow: GlobalNavigationFlow) = when(globalNavigationFlow) {
        GlobalNavigationFlow.HomeFlow -> navController.navigate(NavGraphDirections.actionGlobalHomeFlow())
        GlobalNavigationFlow.RecipeFlow -> navController.navigate(NavGraphDirections.actionGlobalRecipesFlow())
    }
    
    fun navigateToDetailsFlow(navigationFlow: NavigationFlow, id: String) {
        if (navigationFlow == NavigationFlow.RecipeDetailsFlow) {
            navController.navigate(NavGraphDirections.actionRecipeDetailsFlow(id))
        }
    }
}