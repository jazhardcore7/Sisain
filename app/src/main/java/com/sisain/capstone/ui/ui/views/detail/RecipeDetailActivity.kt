package com.sisain.capstone.ui.ui.views.detail

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView


import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sisain.capstone.MainActivity
import com.sisain.capstone.R
import com.sisain.capstone.data.api.response.RecipeResponse
import com.sisain.capstone.data.api.retrofit.RecipeApi
import com.sisain.capstone.data.model.Recipe
import com.sisain.capstone.data.model.Step
import com.sisain.capstone.data.model.StepDet

import com.sisain.capstone.ui.ui.adapters.StepsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var ivRecipeImage: ImageView
    private lateinit var tvRecipeName: TextView
    private lateinit var btnBack: ImageButton
    private lateinit var btnFavourite: ImageButton
    private lateinit var rvSteps: RecyclerView
    private lateinit var stepsAdapter: StepsAdapter

    private lateinit var recipeApi: RecipeApi

    // Dummy steps for local
    private val dummySteps = listOf(
        Step( 1,"Prepare all ingredients"),
        Step( 2,"Cook at medium heat"),
        Step( 3,"Serve and enjoy",)
    )

    // Dummy recipe for local (this can be replaced with dynamic local data)
    private val localRecipe = Recipe("Sample Recipe", R.drawable.ic_logo, dummySteps)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // Initializing views
        ivRecipeImage = findViewById(R.id.iv_recipe_image)
        tvRecipeName = findViewById(R.id.tv_recipe_name)
        btnBack = findViewById(R.id.btn_back)
        btnFavourite = findViewById(R.id.btn_favourite)
        rvSteps = findViewById(R.id.rv_steps)

        // Setup RecyclerView for steps
        rvSteps.layoutManager = LinearLayoutManager(this)

        // Setup Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://recipe-predict-67551552654.asia-southeast1.run.app/")  // Base URL for prediction API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipeApi = retrofit.create(RecipeApi::class.java)

        // Here, instead of using recipeId, use the localRecipe object
        postRecipePrediction(localRecipe)  // Posting the local recipe directly

        // Handle back button
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Handle favorite button click
        btnFavourite.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    private fun postRecipePrediction(recipe: Recipe) {
        val call = recipeApi.postRecipePrediction(recipe)  // Call the POST method for prediction

        call.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    recipeResponse?.let {
                        // Update UI with API data (name, steps)
                        tvRecipeName.text = it.recipe_name

                        // Local image for the recipe
                        ivRecipeImage.setImageResource(R.drawable.cheeseburger_potato_soup)  // Set your local image here

                        // Prepare steps from API data
                        val steps = it.steps.mapIndexed { index, step -> Step(index + 1, step.toString()) }

                        updateStepsRecyclerView(steps)
                    }
                } else {
                    // Handle failure case (maybe show an error message)
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                // Handle failure to get API data
                // Show error or retry
            }
        })
    }

    private fun updateStepsRecyclerView(steps: List<Step>) {
        stepsAdapter = StepsAdapter(steps)
        rvSteps.adapter = stepsAdapter
    }
}