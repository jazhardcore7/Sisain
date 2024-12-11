package com.sisain.capstone.ui.ui.views.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sisain.capstone.R
import com.sisain.capstone.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var ingredientAdapter: IngredientAdapter
    private lateinit var preparationAdapter: PreparationAdapter
    private var isFavourite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get recipe ID from intent
        val recipeId = intent.getStringExtra("RECIPE_ID")
        if (recipeId == null) {
            Toast.makeText(this, "Invalid recipe ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupRecyclerViews()
        loadRecipeDetails(recipeId)

        // Back button listener
        binding.btnBack.setOnClickListener {
            finish() // Back to MainActivity
        }

        // Favourite button listener
        binding.btnFavourite.setOnClickListener {
            toggleFavourite(recipeId)
        }
    }

    private fun setupRecyclerViews() {
        ingredientAdapter = IngredientAdapter()
        binding.rvIngredients.apply {
            layoutManager = LinearLayoutManager(this@RecipeDetailActivity)
            adapter = ingredientAdapter
        }

        preparationAdapter = PreparationAdapter()
        binding.rvPreparation.apply {
            layoutManager = LinearLayoutManager(this@RecipeDetailActivity)
            adapter = preparationAdapter
        }
    }

    private fun loadRecipeDetails(recipeId: String) {
        FirestoreHelper.getRecipeDetails(recipeId) { recipe ->
            if (recipe != null) {
                binding.tvRecipeName.text = recipe.name
                binding.ivRecipeImage.setImageResource(recipe.imageRes) // Replace with image loader if needed
                ingredientAdapter.submitList(recipe.ingredients)
                preparationAdapter.submitList(recipe.preparationSteps)

                // Update favourite button state
                isFavourite = recipe.isFavourite
                updateFavouriteButton()
            } else {
                Toast.makeText(this, "Failed to load recipe details", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun toggleFavourite(recipeId: String) {
        isFavourite = !isFavourite
        FirestoreHelper.updateFavouriteStatus(recipeId, isFavourite) {
            updateFavouriteButton()
            val message = if (isFavourite) "Added to favourites" else "Removed from favourites"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFavouriteButton() {
        val favouriteIcon = if (isFavourite) R.drawable.ic_favourite else R.drawable.ic_favourite_border
        binding.btnFavourite.setImageResource(favouriteIcon)
    }
}