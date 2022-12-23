package com.example.month4_lesson1.ui.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.net.Uri

class Preference(private val context: Context) {
  private  val sharedPref: SharedPreferences = context.getSharedPreferences("prefences", MODE_PRIVATE)

    fun isBoardingShowed(): Boolean{
        return sharedPref.getBoolean("board", false)
    }
    fun setBoardingShowed(isShow: Boolean){
        sharedPref.edit().putBoolean("board", isShow).apply()
    }
    fun getName():String?{
        return sharedPref.getString(SHOW_NAME , "")
    }
    fun saveName(name: String){
        sharedPref.edit().putString(SHOW_NAME , name).apply()
    }
    fun getImg(): String{
        return sharedPref.getString("image", "").toString()
    }
    fun saveImg(imgUri: String){
        return sharedPref.edit().putString("image", imgUri).apply()
    }



    companion object{
        const val SHOW_NAME = "name"

    }

}