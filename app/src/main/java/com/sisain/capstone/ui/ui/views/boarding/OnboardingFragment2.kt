package com.sisain.capstone.ui.ui.views.boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sisain.capstone.R

class OnboardingFragment2 : Fragment(R.layout.fragment_boarding2)
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_boarding2, container, false)

        val nextButton: Button = view.findViewById(R.id.btn_next)
        nextButton.setOnClickListener {
            (activity as? OnBoardingActivity)?.navigateToPage(2)
        }

        return view
    }
}
