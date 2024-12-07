package com.sisain.capstone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sisain.capstone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Navigate to UserProfileActivity when the profile button is clicked
    fun goToUserProfile(view: android.view.View) {
        val intent = Intent(this, UserProfileActivity::class.java)
        startActivity(intent)
    }
}
