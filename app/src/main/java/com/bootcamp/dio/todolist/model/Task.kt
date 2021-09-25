package com.bootcamp.dio.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
)
