package com.sisain.capstone.data.userlocaldb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sisain.capstone.data.model.Step

@Entity(tableName = "favourite_recipes")
data class FavouriteRecipeEntity(
    @PrimaryKey
    @ColumnInfo(name = "recipe_name")
    val recipeName: String,

    @ColumnInfo(name = "recipe_image")
    val recipeImage: Int,

    @ColumnInfo(name = "steps")
    val steps: List<Step>
)