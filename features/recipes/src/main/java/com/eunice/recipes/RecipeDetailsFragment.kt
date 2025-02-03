package com.eunice.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.eunice.recipes.databinding.RecipeDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {
    
    private val recipeDetailsFragmentArgs by navArgs<RecipeDetailsFragmentArgs>()
    private lateinit var recipeDetailsBinding: RecipeDetailsFragmentBinding
    private lateinit var mealDetailsAdapter: MealDetailsAdapter

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
                setupDetails(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.favouriteMealUIState.collectLatest {
                setupBookmark(it)
            }
        }
    }

    private fun setupBookmark(mealUIState
                              : RecipeDetailsViewModel.FavouriteMealUIState) {
        recipeDetailsBinding.ibBookmark.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                if (mealUIState.isFavourite) {
                    viewModel.removeMealFromFavourite(mealUIState.mealId.toString())
                } else {
                    viewModel.markMealAsFavourite(mealUIState.mealId.toString())
                }
                updateBookmark(mealUIState.isFavourite)
            }
        }

        if (mealUIState.favouriteMealName != null) {
            if (mealUIState.isFavourite) {
                Toast.makeText(
                    context,
                    getString(R.string.meal_added_to_fav, mealUIState.favouriteMealName),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.meal_removed_from_fav, mealUIState.favouriteMealName),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (mealUIState.errorMessage != null) {
            if (mealUIState.isFavourite) {
                Toast.makeText(
                    context,
                    getString(R.string.unable_to_remove_from_fav),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                Toast.makeText(context,
                    getString(R.string.unable_to_add_to_fav),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateBookmark(addToFavourite: Boolean) {
        if (addToFavourite) {
            recipeDetailsBinding.ibBookmark.setImageDrawable(
                ContextCompat.getDrawable(requireContext(),
                    cm.eunice.view.R.drawable.ic_bookmark_filled)
            )
        } else {
            recipeDetailsBinding.ibBookmark.setImageDrawable(
                ContextCompat.getDrawable(requireContext(),
                    cm.eunice.view.R.drawable.ic_bookmark)
            )
        }
    }
    
    private fun setupDetails(mealUIState
                             : RecipeDetailsViewModel.MealUIState) {
        
        if (mealUIState.meal != null) {
            Glide
                .with(this@RecipeDetailsFragment)
                .load(mealUIState.meal.mealImg)
                .placeholder(cm.eunice.view.R.drawable.ic_empty_screen)
                .into(recipeDetailsBinding.ivMealImage)

            updateBookmark(mealUIState.isFavourite)
        
            mealDetailsAdapter = MealDetailsAdapter(mealUIState.meal)
            setupLayoutManager()
            recipeDetailsBinding.rvIngredients.adapter = mealDetailsAdapter
        }
    
        if (mealUIState.errorMessage?.isNotBlank() == true) {
            Toast.makeText(context, mealUIState.errorMessage, Toast.LENGTH_LONG).show()
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
    
    companion object {
        fun newInstance() = RecipeDetailsFragment()
    }
}