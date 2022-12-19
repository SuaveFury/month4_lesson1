package com.example.month4_lesson1.ui.onBoarding


import java.io.Serializable

data class OnBoard(
    var image: Int?=null,
    var title: String?=null,
    var description: String?=null,
    var isLast : Boolean?=false,


): Serializable
