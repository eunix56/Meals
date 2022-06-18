package com.eunice.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecipesFragment : Fragment() {

    companion object {
        fun newInstance() = RecipesFragment()
    }

    //The idea is to use this for the search activity and the home activity for the categories

    private lateinit var viewModel: RecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipes_fragment, container, false)
    }


}