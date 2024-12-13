
package com.sisain.capstone.models

data class Recipe(
    val recipe_name: String,
    val steps: List<String>
)

data class PredictionResponse(
    val sentence: String,
    val recipes: List<Recipe>
)
