package com.sisain.capstone.ui.ui.views.boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.sisain.capstone.R


class Boarding2Fragment : Fragment(R.layout.fragment_boarding2) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            // Arahkan ke halaman ketiga
            (activity as? OnBoardingActivity)?.viewPager?.currentItem = 2
        }
    }
}