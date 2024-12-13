package com.sisain.capstone.data.userlocaldb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [FavouriteRecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(StepTypeConverter::class)
abstract class FavouriteRecipeDatabase : RoomDatabase() {
    abstract fun favouriteRecipeDao(): FavouriteRecipeDao

    companion object {
        @Volatile
        private var INSTANCE: FavouriteRecipeDatabase? = null

        fun getDatabase(context: Context): FavouriteRecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteRecipeDatabase::class.java,
                    "favourite_recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}