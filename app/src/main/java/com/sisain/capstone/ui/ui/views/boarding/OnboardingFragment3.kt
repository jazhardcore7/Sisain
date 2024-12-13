package com.sisain.capstone.ui.ui.views.boarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sisain.capstone.R
import com.sisain.capstone.ui.ui.views.auth.LoginActivity
import com.sisain.capstone.ui.ui.views.auth.RegisterActivity

class OnboardingFragment3 : Fragment(R.layout.fragment_boarding3) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_boarding3, container, false)

        val registerButton: Button? = view.findViewById(R.id.btn_register)
        registerButton?.setOnClickListener {
            startActivity(Intent(requireActivity(), RegisterActivity::class.java))
        } ?: run {
            Log.e("OnboardingFragment3", "Register button not found!")
        }

        val loginButton: Button = view.findViewById(R.id.btn_login)
        loginButton.setOnClickListener {

            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }

        return view
    }
}
