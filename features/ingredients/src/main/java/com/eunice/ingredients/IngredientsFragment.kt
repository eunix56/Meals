package com.eunice.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.eunice.domain.model.Ingredient
import com.eunice.ingredients.databinding.FragmentIngredientsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class IngredientsFragment : Fragment() {
    private lateinit var ingredientsBinding: FragmentIngredientsBinding
    private val placesAdapter by lazy { PlacesAdapter {

    } }

    private val adapter by lazy { IngredientsAdapter(placesAdapter) {
        openIngredientDetail(it)
    } }

    private fun openIngredientDetail(ingredient: Ingredient) {
        val directions = ingredient.description?.let {
            IngredientsFragmentDirections.actionIngredientsFragmentToIngredientDetailFragment(
                ingredient.ingredientName, it
            )
        }
        if (directions != null) {
            findNavController().navigate(directions)
        }
    }

    private lateinit var viewModel: IngredientsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[IngredientsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        ingredientsBinding = FragmentIngredientsBinding.inflate(inflater)
        return ingredientsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.placesUIState.collect {
                setPlacesUiState(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.ingredientsUIState.collect {
                setIngredientsUiState(it)
            }
        }
    }

    private fun setPlacesUiState(uiState: IngredientsViewModel.PlacesUIState) {
        if (uiState.places.isNotEmpty()) {
            placesAdapter.submitList(uiState.places)
        }

        if (uiState.errorMessage?.isNotBlank() == true) {

        }
    }

    private fun setIngredientsUiState(uiState: IngredientsViewModel.IngredientsUIState) {
        if (uiState.ingredients.isNotEmpty()) {
            populateIngredients(uiState.ingredients)
        }

        if (uiState.errorMessage?.isNotBlank() == true) {
            Toast.makeText(requireContext(), uiState.errorMessage,
                Toast.LENGTH_LONG).show()
        }
    }

    private fun populateIngredients(ingredient: List<Ingredient>) = with(ingredientsBinding) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == HEADER) 1
                else 2
            }
        }
        rvIngredients.layoutManager = layoutManager
        rvIngredients.adapter = adapter
        adapter.submitList(ingredient)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            IngredientsFragment().apply {

            }
    }
}