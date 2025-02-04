package com.eunice.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.eunice.domain.model.Meal
import com.eunice.ingredients.IngredientDetailViewModel.IngredientMealsUIState
import com.eunice.ingredients.databinding.FragmentIngredientDetailBinding
import com.eunice.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class IngredientDetailFragment : Fragment() {
    private lateinit var detailBinding: FragmentIngredientDetailBinding

    @Inject
    lateinit var navigator: Navigator
    private val args by navArgs<IngredientDetailFragmentArgs>()
    private lateinit var viewModel: IngredientDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[IngredientDetailViewModel::class.java]
        viewModel.fetchIngredientMeals(args.ingredientName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        detailBinding = FragmentIngredientDetailBinding.inflate(inflater)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.ingredientMealsUIState.collectLatest {
                setupIngredientMealsUiState(it)
            }
        }

        setupView()
    }

    private fun setupView() = with(detailBinding) {
        Glide
            .with(ivIngredientImg)
            .load("https://www.themealdb.com/images/ingredients/${args.ingredientName}.png")
            .placeholder(cm.eunice.view.R.drawable.ing)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(ivIngredientImg)

        tvIngredientName.text = args.ingredientName
        tvIngredientDescText.text = args.ingredientDesc

        tvIngredientReadMore.setOnClickListener {
            tvIngredientDescText.maxLines = Int.MAX_VALUE
            tvIngredientReadMore.visibility = View.GONE
        }
    }

    private fun setupIngredientMealsUiState(uiState: IngredientMealsUIState) {
        if (uiState.meals.isEmpty()) {
            setupAdapter(uiState.meals)
        }

        if (uiState.errorMessage?.isNotBlank() == true) {
            Toast.makeText(context, uiState.errorMessage.toString(),
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupAdapter(meals: List<Meal>) = with(detailBinding) {
        val adapter = MealsWithIngredientAdapter {
            navigator.navigateToRecipesDetailsFlow(it)
        }
        adapter.submitList(meals)
        rvMealsWithIng.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = IngredientDetailFragment()
    }
}