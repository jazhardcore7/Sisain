package com.sisain.capstone.ui.ui.views.home.favourites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sisain.capstone.R
import com.sisain.capstone.databinding.FragmentFavouriteBinding
import com.sisain.capstone.ui.detail.RecipeDetailActivity
import com.sisain.capstone.ui.profile.ProfileActivity
import com.sisain.capstone.data.model.Recipe // Replace with your Recipe model
import com.sisain.capstone.data.firebase.FirestoreHelper // Helper class for Firestore integration

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupBottomNavigation()
        fetchFavourites()


        binding.ivProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        favouriteAdapter = FavouriteAdapter { recipe ->
            // Navigate to RecipeDetailActivity
            val intent = Intent(requireContext(), RecipeDetailActivity::class.java)
            intent.putExtra("RECIPE_ID", recipe.id)
            startActivity(intent)
        }

        binding.rvFavourite.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = favouriteAdapter
        }
    }

    private fun fetchFavourites() {
        FirestoreHelper.getFavouriteRecipes { favourites ->
            if (favourites.isNotEmpty()) {
                binding.rvFavourite.visibility = View.VISIBLE
                binding.empty_state.visibility = View.GONE
                favouriteAdapter.submitList(favourites)
            } else {
                binding.rvFavourite.visibility = View.GONE
                binding.empty_state.visibility = View.VISIBLE
            }
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.selectedItemId = R.id.nav_favourite
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
