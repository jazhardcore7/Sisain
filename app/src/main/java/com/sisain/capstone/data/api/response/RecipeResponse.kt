package com.sisain.capstone.data.api.response

data class RecipeResponse(
    val recipe_name: String,
    val steps: List<String> // Or a different type if steps are not strings
)
