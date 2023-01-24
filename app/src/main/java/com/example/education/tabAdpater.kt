package com.example.education

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class tabAdpater(fm: FragmentActivity): FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> LoginFragment()
            1 -> SignupFragment()
            else -> LoginFragment()
        }
    }

}