package com.example.recipes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.navigation.NavigationFlow
import com.example.recipes.databinding.RecipeDetailsFragmentBinding
import kotlinx.coroutines.flow.collect

class RecipeDetailsFragment : Fragment() {
    
    private val recipeDetailsFragmentArgs by navArgs<RecipeDetailsFragmentArgs>()
    private lateinit var recipeDetailsBinding: RecipeDetailsFragmentBinding

    companion object {
        fun newInstance() = RecipeDetailsFragment()
    }

    private lateinit var viewModel: RecipeDetailsViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    
        viewModel = ViewModelProvider(this)[RecipeDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recipeDetailsBinding = RecipeDetailsFragmentBinding.inflate(inflater)
        return recipeDetailsBinding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        lifecycleScope.launchWhenStarted {
            recipeDetailsFragmentArgs.mealId?.let { viewModel.getMealById(it) }
        }
        
        lifecycleScope.launchWhenStarted {
            viewModel.mealUIState.collect {
                if (it.meal != null) {
                    val mealDetailsAdapter = MealDetailsAdapter(it.meal)
                    recipeDetailsBinding.rvIngredients.adapter = mealDetailsAdapter
                }
    
                if (it.errorMessage?.isNotBlank() == true) {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    
}