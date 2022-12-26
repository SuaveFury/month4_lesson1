package com.example.month4_lesson1.ui.home

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var title: String,
    var description: String,
    var imgUri: String
) : Serializable
