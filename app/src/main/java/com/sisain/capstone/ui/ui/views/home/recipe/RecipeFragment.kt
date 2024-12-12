package com.sisain.capstone.ui.ui.views.home.recipe

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisain.capstone.R
import com.sisain.capstone.data.model.Recipe
import com.sisain.capstone.ui.ui.adapters.RecipeAdapter

class RecipeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.rv_recipe)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // 2 columns

        // Dummy Data
        val dummyRecipes = listOf(
            Recipe("Broccoli Salad", R.drawable.broccoli_salad),
            Recipe("Cheeseburger Potato Soup",  R.drawable.cheeseburger_potato_soup),
            Recipe("Fresh Strawberry Pie",  R.drawable.fresh_strawberry_pie),
            Recipe("Quick Barbecue Wings",  R.drawable.quick_barbecue_wings),
            Recipe("Rhubarb Coffee Cake",  R.drawable.rhubarb_coffee_cake),
            Recipe("Smoked Turkey Risotto", R.drawable.smoked_turkey_risotto)
        )

        // Set Adapter
        recipeAdapter = RecipeAdapter(dummyRecipes)
        recyclerView.adapter = recipeAdapter

        return view
    }
}