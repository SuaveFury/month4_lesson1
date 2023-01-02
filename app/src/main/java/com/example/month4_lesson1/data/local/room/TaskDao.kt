package com.example.month4_lesson1.data.local.room

import androidx.room.*
import com.example.month4_lesson1.ui.home.TaskModel

@Dao
interface TaskDao {

    @Insert
    fun insert(task: TaskModel)

    @Query("SELECT * FROM TaskModel")
    fun getAllTasks():List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY id DESC")
    fun getListByDate():List<TaskModel>

    @Query("SELECT * FROM TaskModel ORDER BY  title ASC")
    fun getListByAlphabet(): List<TaskModel>

    @Delete
    fun deleteTask(task: TaskModel)

    @Update
    fun updateTask(taskModel: TaskModel)
}