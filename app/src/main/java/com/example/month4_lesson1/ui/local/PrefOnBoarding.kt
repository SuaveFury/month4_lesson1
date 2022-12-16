package com.example.month4_lesson1.ui.local

import android.content.Context
import android.content.SharedPreferences

class PrefOnBoarding(private val context: Context) {
    private var pref: SharedPreferences =
        context.getSharedPreferences(PREF_BOARD, Context.MODE_PRIVATE)



    fun getName(): String? {
        return pref.getString(SHOW_NAME, "")
    }

    fun saveName(name: String) {
        pref.edit().putString(SHOW_NAME, name).apply()
    }



    companion object {
        const val PREF_BOARD = "pref.task"
        const val SHOW_NAME = "name"
    }
}
