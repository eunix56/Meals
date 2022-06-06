package com.example.recipes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.navigation.NavigationFlow
import com.example.recipes.databinding.RecipeDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {
    
    private val recipeDetailsFragmentArgs by navArgs<RecipeDetailsFragmentArgs>()
    private lateinit var recipeDetailsBinding: RecipeDetailsFragmentBinding
    private lateinit var mealDetailsAdapter: MealDetailsAdapter

    companion object {
        fun newInstance() = RecipeDetailsFragment()
    }

    private val viewModel by viewModels<RecipeDetailsViewModel>()
    

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
                    Glide
                        .with(this@RecipeDetailsFragment)
                        .load(it.meal.mealImg)
                        .placeholder(R.drawable.ic_empty_screen)
                        .into(recipeDetailsBinding.ivMealImage)
                    
                    mealDetailsAdapter = MealDetailsAdapter(it.meal)
                    setupLayoutManager()
                    recipeDetailsBinding.rvIngredients.adapter = mealDetailsAdapter
                }
    
                if (it.errorMessage?.isNotBlank() == true) {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun setupLayoutManager() {
        val gridManager = GridLayoutManager(context, 3)
        gridManager.orientation = GridLayoutManager.VERTICAL
        gridManager.spanSizeLookup  = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
    
                return when (mealDetailsAdapter.getItemViewType(position)){
                    INGREDIENTS -> 1
                    else -> 3
                }
            }
        }
        recipeDetailsBinding.rvIngredients.layoutManager = gridManager
    }

    
}