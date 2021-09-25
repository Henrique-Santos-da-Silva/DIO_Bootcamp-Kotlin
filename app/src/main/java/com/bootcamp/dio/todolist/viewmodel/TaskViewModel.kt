package com.bootcamp.dio.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bootcamp.dio.todolist.datasource.AppDatabase
import com.bootcamp.dio.todolist.model.Task
import com.bootcamp.dio.todolist.repositories.TaskRepository
import com.bootcamp.dio.todolist.repositories.TaskRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val repository: TaskRepository
    private var readAll: LiveData<List<Task>>

    init {
        val taskDB = AppDatabase(application).taskDao()
        repository = TaskRepositoryImpl(taskDB)
        readAll = repository.getAll()
    }

    fun getAll(): LiveData<List<Task>> {
        return readAll
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTask(task)
        }
    }

    fun editTask(title: String, description: String, date: String, time: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editTask(title, description, date, time, id)
        }
    }

    fun editTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editTask(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

    fun findById(id: Int): LiveData<Task> {
        return repository.findById(id)
    }

}