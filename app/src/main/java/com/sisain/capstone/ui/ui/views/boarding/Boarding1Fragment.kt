package com.sisain.capstone.ui.ui.views.boarding

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.sisain.capstone.R


class Boarding1Fragment : Fragment (R.layout.fragment_boarding1) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            // Arahkan ke halaman kedua
            (activity as? OnBoardingActivity)?.viewPager?.currentItem = 1
        }
    }
}