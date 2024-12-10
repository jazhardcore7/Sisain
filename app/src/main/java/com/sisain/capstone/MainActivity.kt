package com.sisain.capstone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sisain.capstone.databinding.ActivityMainBinding

import com.sisain.capstone.ui.ui.views.boarding.OnBoardingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("onboarding_pref", MODE_PRIVATE)
        val isOnboardingCompleted = sharedPreferences.getBoolean("isOnboardingCompleted", false)

        if (isOnboardingCompleted) {
            // Jika sudah, tampilkan MainActivity
            setContentView(R.layout.activity_main)
        } else {
            // Jika belum, arahkan ke OnBoardingActivity
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish() // Menutup MainActivity agar tidak bisa kembali ke halaman Main
        }
    }


}
