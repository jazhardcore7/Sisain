package com.sisain.capstone.ui.ui.views.home.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisain.capstone.R
import com.sisain.capstone.data.api.response.RecipeResponse
import com.sisain.capstone.data.api.retrofit.RecipeApi
import com.sisain.capstone.data.model.Recipe
import com.sisain.capstone.data.model.Step
import com.sisain.capstone.ui.ui.adapters.RecipeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeApi: RecipeApi

    private val dummyRecipes = listOf(
        Recipe("Broccoli Salad", R.drawable.broccoli_salad, listOf(
            Step(1,"Step 1: Prepare broccoli"),
            Step(2,"Step 2: Add dressing"),
            Step(3,"Step 3: Serve")
        )),
        Recipe("Cheeseburger Potato Soup", R.drawable.broccoli_salad, listOf(
            Step(1,"Step 1: Cook potatoes"),
            Step(2,"Step 2: Prepare cheese sauce"),
            Step(3,"Step 3: Combine and serve")
        )),
        Recipe("Fresh Strawberry Pie", R.drawable.broccoli_salad, listOf(
            Step(1,"Step 1: Prepare pie crust"),
            Step(2,"Step 2: Mix strawberries"),
            Step(3,"Step 3: Assemble and chill")
        )),
        Recipe("Quick Barbecue Wings", R.drawable.broccoli_salad, listOf(
            Step(1,"Step 1: Marinate chicken"),
            Step(2,"Step 2: Grill wings"),
            Step(3,"Step 3: Serve with barbecue sauce")
        )),
        Recipe("Rhubarb Coffee Cake", R.drawable.broccoli_salad, listOf(
            Step(1,"Step 1: Prepare cake batter"),
            Step(2,"Step 2: Add rhubarb"),
            Step(3,"Step 3: Bake and serve")
        )),
        Recipe("Smoked Turkey Risotto", R.drawable.broccoli_salad, listOf(
            Step(1,"Step 1: Cook rice"),
            Step(2,"Step 2: Prepare turkey"),
            Step(3,"Step 3: Combine and serve")
        ))
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.rv_recipe)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        // Setup Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://recipe-predict-67551552654.asia-southeast1.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipeApi = retrofit.create(RecipeApi::class.java)

        // Setup Search Bar
        val searchButton = view.findViewById<ImageButton>(R.id.iv_search)
        val searchEditText = view.findViewById<EditText>(R.id.et_search)


        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                searchRecipe(query)
            }
        }

        // Default: Load local data
        recipeAdapter = RecipeAdapter(dummyRecipes)
        recyclerView.adapter = recipeAdapter

        return view
    }

    private fun searchRecipe(query: String) {
        val call = recipeApi.getRecipe(query)

        call.enqueue(object : Callback<List<RecipeResponse>> {
            override fun onResponse(call: Call<List<RecipeResponse>>, response: Response<List<RecipeResponse>>) {
                if (response.isSuccessful) {
                    val recipes = response.body()
                    if (recipes != null) {
                        val recipeNames = recipes.map {
                            // Convert each step to a Step object
                            val steps = it.steps.mapIndexed { index, stepDescription ->
                                Step(index + 1, stepDescription) // Create a Step object with a number and description
                            }
                            // Now pass the steps as a List<Step> to the Recipe constructor
                            Recipe(it.recipe_name, R.drawable.broccoli_salad, steps)
                        }
                        updateRecyclerView(recipeNames)
                    }
                }
            }

            override fun onFailure(call: Call<List<RecipeResponse>>, t: Throwable) {
                // Handle failure
            }
        })
    }



    private fun updateRecyclerView(recipeNames: List<Recipe>) {
        recipeAdapter = RecipeAdapter(recipeNames)
        recyclerView.adapter = recipeAdapter
    }
}
