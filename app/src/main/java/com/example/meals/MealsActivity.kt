package com.example.meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import meals.R
import meals.databinding.ActivityMealsBinding

//Learn RxJava, Flow, Room, Hilt, Navigation components
//What are the features you intend to add?
class MealsActivity : AppCompatActivity() {
    private lateinit var activityMealsBinding:
            ActivityMealsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals)

        activityMealsBinding = ActivityMealsBinding.inflate(LayoutInflater.from(this))
        val navController by lazy { findNavController(R.id.nav_host_fragment_container) }
        activityMealsBinding.bnvMeals.setupWithNavController(navController)
    }
}