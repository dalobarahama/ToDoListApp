package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.utils.ToDoData
import com.example.todolist.viewmodel.ToDoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val todoListAdapter = ToDoAdapter(arrayListOf())
    private lateinit var viewModel: ToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivityForResult(intent, 100)
        }

//        viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)

        viewModel = ViewModelProvider(
            ViewModelStore(),
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(
            ToDoViewModel::class.java
        )
        addData()
        viewModel.refresh()

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.todos.observe(this, Observer { todos ->
            todos?.let {
                todoListAdapter.updateToDoList(todos)
            }
        })
    }

    private fun addData() {
        viewModel.storeToDoLocally(ToDoData.listData)
    }

}
