package com.sisain.capstone

import androidx.fragment.app.Fragment
import com.sisain.capstone.ui.ui.views.home.favourites.FavouriteFragment
import com.sisain.capstone.ui.ui.views.home.recipe.RecipeFragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipeFragment()) // Display RecipeFragment first
                .commit()
        }

        // Handle bottom navigation selection
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_recipe -> {
                    selectedFragment = RecipeFragment()
                }
                R.id.nav_favourite -> {
                    selectedFragment = FavouriteFragment()
                }
            }

            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
            }
            true
        }
    }
}

