package com.eunice.meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.eunice.meals.databinding.ActivityMealsBinding
import com.eunice.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Learn RxJava, Flow, Room, Hilt, Navigation components
//What are the features you intend to add?
@AndroidEntryPoint
class MealsActivity : AppCompatActivity() {
    
    @Inject
    lateinit var navigator: Navigator
    
    private lateinit var activityMealsBinding:
            ActivityMealsBinding
    private lateinit var navController: NavController
    private val topLevels = listOf(com.eunice.home.R.id.homeFragment,
        com.eunice.recipes.R.id.recipesFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMealsBinding = ActivityMealsBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMealsBinding.root)

        val navHostFragment =  supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        navigator.navController = navController
        activityMealsBinding.bnvMeals.setupWithNavController(navController)
    }
}