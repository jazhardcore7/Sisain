package com.sisain.capstone.ui.ui.views.boarding

import com.sisain.capstone.ui.ui.views.boarding.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2  // Ada tiga halaman (First, Second, Third)
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Boarding1Fragment() // Halaman pertama
            1 -> Boarding2Fragment() // Halaman kedua
            else -> Boarding3Fragment() // Halaman ketiga
        }
    }
}

