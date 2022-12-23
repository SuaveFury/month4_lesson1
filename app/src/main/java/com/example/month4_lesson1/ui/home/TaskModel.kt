package com.example.month4_lesson1.ui.home

import java.io.Serializable

data class TaskModel(
    var title: String,
    var description: String,
    var imgUri: String
) : Serializable
