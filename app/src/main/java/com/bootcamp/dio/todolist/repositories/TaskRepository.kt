package com.bootcamp.dio.todolist.repositories

import androidx.lifecycle.LiveData
import com.bootcamp.dio.todolist.model.Task

interface TaskRepository {
    suspend fun createTask(task: Task)

    suspend fun editTask(title: String, description: String, date: String, time: String, id: Int)

    suspend fun editTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun findById(id: Int): LiveData<Task>

    fun getAll(): LiveData<List<Task>>

}