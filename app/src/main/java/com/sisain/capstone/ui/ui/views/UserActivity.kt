package com.sisain.capstone.ui.ui.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sisain.capstone.MainActivity
import com.sisain.capstone.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Set up "Rate Us" and "About Us" click listeners
        binding.aboutUsRow.setOnClickListener {
            val intent = Intent(this, CreatorActivity::class.java)
            startActivity(intent)
        }

        binding.rateUsRow.setOnClickListener {
            val uri = Uri.parse("https://forms.gle/YOUR_GOOGLE_FORM_LINK")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        binding.logoutRow.setOnClickListener {
            // Handle log out functionality here
        }
    }
}