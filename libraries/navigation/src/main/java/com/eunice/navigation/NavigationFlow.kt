package com.eunice.navigation


/**
 * Created by {EUNICE BAKARE T.} on {5/29/22}
 * Email: {eunice@reach.africa}
 */

sealed class GlobalNavigationFlow {
    object HomeFlow: GlobalNavigationFlow()
    object RecipeFlow: GlobalNavigationFlow()
}

sealed class NavigationFlow {
    object RecipeDetailsFlow: NavigationFlow()
    object IngredientDetailsFlow: NavigationFlow()
}