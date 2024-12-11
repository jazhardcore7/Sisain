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

        // Mengatur tombol kembali
        val backButton = view.findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            // Kembali ke halaman sebelumnya
            requireActivity().onBackPressed()
        }

        // Mengatur tombol Create Account
        val createAccountButton = view.findViewById<Button>(R.id.create_account_button)
        createAccountButton.setOnClickListener {
            // Arahkan ke Create Account Activity
            startActivity(Intent(requireContext(), RegisterActivity::class.java))
        }

        // Mengatur tombol Login
        val loginButton = view.findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            // Arahkan ke Login Activity
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }
}
