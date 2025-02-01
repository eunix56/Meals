package com.eunice.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.eunice.domain.model.Meal
import com.eunice.favourites.databinding.FavouriteFragmentBinding
import com.eunice.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : Fragment() {

    private lateinit var binding: FavouriteFragmentBinding
    private lateinit var viewModel: FavouriteViewModel

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        viewModel.getFavouriteMeals()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FavouriteFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.mealsUIState.collectLatest {
                setupFavouriteMeals(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.favouriteMealUIState.collectLatest {
                viewModel.getFavouriteMeals()
            }
        }
    }

    private fun setupFavouriteMeals(uiState: FavouriteViewModel.MealsUIState) {
        if (uiState.meals?.isNotEmpty() == true) {
            setupAdapter(uiState.meals)
        }

        if (uiState.errorMessage?.isNotBlank() == true) {
            Toast.makeText(context, uiState.errorMessage.toString(),
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupAdapter(meals: List<Meal>) {
        val favouriteAdapter: FavouriteAdapter by lazy {
            FavouriteAdapter ({
                navigator.navigateToRecipesDetailsFlow(it)
            }) {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    viewModel.removeMealFromFavourite(it)
                }
            }
        }
        favouriteAdapter.submitList(meals)
        binding.rvFavouriteMeals.adapter = favouriteAdapter
    }


    companion object {
    }

}