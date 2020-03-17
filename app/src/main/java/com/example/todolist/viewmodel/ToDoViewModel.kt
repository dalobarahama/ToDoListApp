package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todolist.model.ToDo
import com.example.todolist.model.ToDoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by pengalatdite on 28-Feb-20.
 */
class ToDoViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val todos = MutableLiveData<List<ToDo>>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun refresh() {
        fetchFromDatabase()
    }

    private fun fetchFromDatabase() {
        launch {
            val todos = ToDoDatabase(getApplication()).toDoDao().getAllToDoList()

            todosRetrieved(todos)
        }
    }

    private fun todosRetrieved(todoList: List<ToDo>) {
        todos.value = todoList
    }

    fun storeToDoLocally(todoList: List<ToDo>) {
        launch {
            val dao = ToDoDatabase(getApplication()).toDoDao()
            val result = dao.insertAll(*todoList.toTypedArray())
            var i = 0
            while (i < todoList.size) {
                todoList[i].id = result[i].toInt()
                ++i
            }
            todosRetrieved(todoList)
        }
    }

}