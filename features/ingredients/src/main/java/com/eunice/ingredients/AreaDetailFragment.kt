package com.eunice.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eunice.domain.model.Meal
import com.eunice.ingredients.databinding.FragmentAreaDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AreaDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentAreaDetailBinding
    private lateinit var viewModel: AreaDetailViewModel

    private val areaDetailFragmentArgs by navArgs<AreaDetailFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[AreaDetailViewModel::class.java]
        viewModel.fetchAreaDetailMeals(areaDetailFragmentArgs.areaName)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        detailBinding = FragmentAreaDetailBinding.inflate(inflater)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.areaMealsUIState.collectLatest { uiState ->
                setupAreaDetailUiState(uiState)
            }
        }
    }

    private fun setupAreaDetailUiState(uiState: AreaDetailViewModel.AreaMealsUIState) =
        with(detailBinding) {
            if (uiState.meals.isEmpty()) {
                setupAdapter(uiState.meals)
            }

            if (uiState.errorMessage?.isNotBlank() == true) {
                Toast.makeText(context, uiState.errorMessage.toString(),
                    Toast.LENGTH_SHORT).show()
            }

    }

    private fun setupAdapter(meals: List<Meal>) =
        with(detailBinding) {
            tvAreaName.text = areaDetailFragmentArgs.areaName
            val areaDetailMealsAdapter = AreaDetailMealsAdapter()
            rvAreaMeals.adapter = areaDetailMealsAdapter
            areaDetailMealsAdapter.submitList(meals)

            val otherAreasMealsAdapter = OtherAreasMealsAdapter {
                val directions = AreaDetailFragmentDirections.actionAreaDetailFragmentSelf(
                    it, areaDetailFragmentArgs.names
                )
                findNavController().navigate(directions)
            }
            rvMealsFromPlaces.adapter = otherAreasMealsAdapter
            otherAreasMealsAdapter.submitList(areaDetailFragmentArgs.names.toList())
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AreaDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}