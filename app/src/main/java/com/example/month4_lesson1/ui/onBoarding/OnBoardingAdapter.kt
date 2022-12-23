package com.example.month4_lesson1.ui.onBoarding

import androidx.core.graphics.toColor
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.month4_lesson1.R


class OnBoardingAdapter(fm: FragmentManager,
var listenerSkip : () -> Unit,
var listenerNext: () -> Unit) : FragmentStatePagerAdapter(fm){

    private val listBoarding = arrayOf(OnBoard(R.drawable.ic_img1,
        "To-do list!",
        "Here you can write down something important or make a schedule for tomorrow:)",
        false
    ),
    OnBoard(R.drawable.ic_img2,
    "Share your crazy idea ^_^",
    "You can easily share with your report, list or schedule and it's convenient",
    false),

        OnBoard(
            R.drawable.ic_img3,
        "Flexibility",
        "Your note with you at home, at work, even at the resort",
        true)
    )



    override fun getCount(): Int = listBoarding.size

    override fun getItem(position: Int): Fragment {
        val data = bundleOf("onBoard" to listBoarding[position])
        val fragment = OnBoardPageFragment(listenerSkip, listenerNext)
        fragment.arguments = data
        return fragment
    }


}
