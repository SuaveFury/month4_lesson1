package com.example.month4_lesson1.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.month4_lesson1.ui.home.TaskModel

@Dao
interface TaskDao {

    @Insert
    fun insert(task: TaskModel)

    @Query("SELECT * FROM TaskModel")
    fun getAllTasks():List<TaskModel>
}