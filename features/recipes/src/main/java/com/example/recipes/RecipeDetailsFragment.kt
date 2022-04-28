package com.example.recipes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecipeDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = RecipeDetailsFragment()
    }

    //The idea is to use this for the search activity and the home activity for the categories

    private lateinit var viewModel: RecipeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[RecipeDetailsViewModel::class.java]
        // TODO: Use the ViewModel
    }

}