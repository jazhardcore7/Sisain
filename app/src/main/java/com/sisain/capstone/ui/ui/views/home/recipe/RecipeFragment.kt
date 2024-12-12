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
            Recipe("Chicken Egg Scramble", "https://drive.google.com/file/d/1pAn9XGUWBx6Y-j1PkMGPCuLY-jf0u83_/view?usp=sharing"),
            Recipe("Beef Steak",  "https://example.com/image2.jpg"),
            Recipe("Salad Bowl",  "https://example.com/image3.jpg"),
            Recipe("Pancake",  "https://example.com/image4.jpg")
        )

        // Set Adapter
        recipeAdapter = RecipeAdapter(dummyRecipes)
        recyclerView.adapter = recipeAdapter

        return view
    }
}