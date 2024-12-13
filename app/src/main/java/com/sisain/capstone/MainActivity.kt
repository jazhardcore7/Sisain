package com.sisain.capstone

import androidx.fragment.app.Fragment
import com.sisain.capstone.ui.ui.views.home.favourites.FavouriteFragment
import com.sisain.capstone.ui.ui.views.home.recipe.RecipeFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Set default fragment (RecipeFragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RecipeFragment())
                .commit()
        }

        // Handle bottom navigation item clicks
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_recipe -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, RecipeFragment())
                        .commit()
                    true
                }
                R.id.nav_favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, FavouriteFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
