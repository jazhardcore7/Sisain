package com.sisain.capstone.data.userlocaldb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteRecipe(recipe: FavouriteRecipeEntity)

    @Query("DELETE FROM favourite_recipes WHERE recipe_name = :recipeName")
    suspend fun deleteFavouriteRecipe(recipeName: String)

    @Query("SELECT * FROM favourite_recipes")
    fun getAllFavouriteRecipes(): Flow<List<FavouriteRecipeEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_recipes WHERE recipe_name = :recipeName)")
    suspend fun isFavourite(recipeName: String): Boolean
}