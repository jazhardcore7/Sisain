package com.sisain.capstone.data.api.retrofit

import com.sisain.capstone.data.api.response.RecipeResponse
import com.sisain.capstone.data.model.Recipe
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecipeApi {
    @GET("sentence") // Replace with the correct API endpoint
    fun getRecipe(@Query("query") query: String): Call<List<RecipeResponse>>

    @POST("predict")
    fun postRecipePrediction(@Body recipe: Recipe): Call<RecipeResponse>
}
