package com.bootcamp.dio.todolist.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bootcamp.dio.todolist.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun findById(id: Int): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("UPDATE task SET title = :title, description = :description, date = :date, time = :time WHERE id = :id")
    suspend fun updateTask(title: String, description: String, date: String, time: String, id: Int)

    @Delete
    suspend fun deleteTask(task: Task)
}