package com.sisain.capstone.data.api

import com.sisain.capstone.data.api.response.RecipeResponse
import com.sisain.capstone.ui.ui.views.detail.RecipeRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("recipes")
    fun createRecipe(@Body recipe: RecipeRequest): Call<RecipeResponse>
}