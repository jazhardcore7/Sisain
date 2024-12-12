package com.sisain.capstone.ui.ui.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sisain.capstone.R
import com.sisain.capstone.databinding.ActivityCreatorBinding

class CreatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator)
        val binding = ActivityCreatorBinding.inflate(layoutInflater)

        // Set up back button
        binding.backButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
    }
}