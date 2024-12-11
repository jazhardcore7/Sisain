package com.sisain.capstone.ui.ui.views.boarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.sisain.capstone.MainActivity
import com.sisain.capstone.R
import com.sisain.capstone.ui.ui.views.auth.LoginActivity
import com.sisain.capstone.ui.ui.views.auth.RegisterActivity


class OnBoardingActivity : AppCompatActivity() {

     lateinit var viewPager: ViewPager2
    private lateinit var onboardingAdapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set konten OnBoardingActivity
        setContentView(R.layout.activity_onboarding)

        // Setup ViewPager2 dan adapter-nya
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = OnboardingAdapter(this)
        viewPager.adapter = adapter

        // Menambahkan Listener pada halaman terakhir
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) { // Halaman ketiga (index 2)
                    val sharedPreferences = getSharedPreferences("onboarding_pref", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isOnboardingCompleted", true)
                    editor.apply()

                    // Bisa arahkan ke MainActivity setelah onboarding selesai
                    startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
                    finish() // Menutup OnBoardingActivity
                }
            }
        })
        val createAccountButton = findViewById<Button>(R.id.btn_create_account)
        createAccountButton.setOnClickListener {
        startActivity(Intent(this, RegisterActivity::class.java))
        }
        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

