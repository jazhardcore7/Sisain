package com.sisain.capstone.ui.ui.views.boarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.sisain.capstone.R
import com.sisain.capstone.ui.ui.views.auth.LoginActivity
import com.sisain.capstone.ui.ui.views.auth.RegisterActivity


class Boarding3Fragment : Fragment(R.layout.fragment_boarding3) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            // Navigasi ke MainActivity atau halaman sebelumnya
            (activity as? OnBoardingActivity)?.onBackPressed()
        }

        val createAccountButton = view.findViewById<Button>(R.id.create_account_button)
        createAccountButton.setOnClickListener {
            // Navigasi ke halaman pembuatan akun
            startActivity(Intent(context, RegisterActivity::class.java))
        }

        val loginButton = view.findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            // Navigasi ke halaman login
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}
