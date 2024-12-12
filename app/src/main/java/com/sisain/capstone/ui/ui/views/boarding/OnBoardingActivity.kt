package com.sisain.capstone.ui.ui.views.boarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.sisain.capstone.MainActivity
import com.sisain.capstone.R
import com.sisain.capstone.ui.ui.views.auth.LoginActivity
import com.sisain.capstone.ui.ui.views.auth.RegisterActivity
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        val dotsIndicator: DotsIndicator = findViewById(R.id.dotsIndicator)

        val adapter = OnboardingFragmentAdapter(this)
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)
    }

    fun navigateToPage(pageIndex: Int) {
        viewPager.currentItem = pageIndex
    }

    fun finishOnboarding() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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
