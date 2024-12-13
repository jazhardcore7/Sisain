package com.sisain.capstone.ui.ui.views.home.favourites


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sisain.capstone.R


class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // BottomNavigationView setup
        val bottomNav: BottomNavigationView = view.findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.recipe_left -> {
                    // Handle menu item 1 click
                    true
                }
                R.id.recipe_right -> {
                    // Handle menu item 2 click
                    true
                }
                // Add other menu items as needed
                else -> false
            }
        }
    }
}
