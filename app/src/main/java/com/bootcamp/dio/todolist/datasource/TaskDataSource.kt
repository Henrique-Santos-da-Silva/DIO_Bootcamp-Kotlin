package com.bootcamp.dio.todolist.datasource

import com.bootcamp.dio.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {
        if (task.id == 0) {
            list.add(task.copy(id = list.size + 1))
        } else {
            list.remove(task)
            list.add(task)
        }

    }

    fun findById(taskId: Int) = list.find { it.id == taskId }

    fun removeTask(task: Task) = list.remove(task)

}