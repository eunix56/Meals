package com.eunice.meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.eunice.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import meals.R
import meals.databinding.ActivityMealsBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMealsBinding = ActivityMealsBinding.inflate(LayoutInflater.from(this))
        setContentView(activityMealsBinding.root)

        val navHostFragment =  supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        
        navigator.navController = navController
    }
}