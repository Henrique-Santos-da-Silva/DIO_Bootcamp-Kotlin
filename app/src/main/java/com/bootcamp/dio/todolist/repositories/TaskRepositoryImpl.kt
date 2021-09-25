package com.bootcamp.dio.todolist.repositories

import androidx.lifecycle.LiveData
import com.bootcamp.dio.todolist.datasource.TaskDao
import com.bootcamp.dio.todolist.model.Task

class TaskRepositoryImpl(private val dao: TaskDao): TaskRepository {

    override suspend fun createTask(task: Task) = dao.insertTask(task)

    override suspend fun editTask(title: String, description: String, date: String, time: String, id: Int) =
        dao.updateTask(title, description, date, time, id)

    override suspend fun editTask(task: Task) = dao.updateTask(task)

    override suspend fun deleteTask(task: Task) = dao.deleteTask(task)

    override fun findById(id: Int): LiveData<Task> = dao.findById(id)

    override fun getAll(): LiveData<List<Task>> = dao.getAll()
}