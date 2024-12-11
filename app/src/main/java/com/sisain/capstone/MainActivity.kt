package com.sisain.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sisain.capstone.ui.favourite.FavouriteFragment
import com.sisain.capstone.ui.ui.views.home.favourites.FavouriteFragment
import com.sisain.capstone.ui.ui.views.home.recipe.RecipeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set default fragment (RecipeFragment)
        loadFragment(RecipeFragment())

        // Setup BottomNavigationView
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_recipe -> {
                    loadFragment(RecipeFragment())
                    true
                }
                R.id.nav_favourite -> {
                    loadFragment(FavouriteFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

