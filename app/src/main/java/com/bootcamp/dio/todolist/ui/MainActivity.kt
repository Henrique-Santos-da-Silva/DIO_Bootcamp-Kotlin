package com.bootcamp.dio.todolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.dio.todolist.databinding.ActivityMainBinding
import com.bootcamp.dio.todolist.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val taskAdapter by lazy { TaskAdapterList() }

    private val viewModel by lazy { TaskViewModel(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = taskAdapter
        updateList()

        insertListerners()
    }

    private fun insertListerners() {
        binding.fabCreateTask.setOnClickListener {
            onActivityResult.launch(Intent(this, AddTaskActivity::class.java))
        }

        taskAdapter.listenerEdit = { task ->
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, task.id)
            onActivityResult.launch(Intent(intent))

        }

        taskAdapter.listenerDelete = { task ->
            // TaskDataSource.removeTask(it)

            viewModel.delete(task)

            updateList()
        }
    }

    private val onActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) updateList()
    }

    private fun updateList() {

        viewModel.getAll().observe(this, Observer { list ->
            binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            taskAdapter.submitList(list)
        })

    }
}