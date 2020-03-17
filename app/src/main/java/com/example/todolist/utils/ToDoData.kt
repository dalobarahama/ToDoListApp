package com.example.todolist.utils

import com.example.todolist.model.ToDo

/**
 * Created by pengalatdite on 02-Mar-20.
 */
object ToDoData {
    private val todos = arrayOf(
        "Ahmad Dahlan",
        "Ahmad Yani",
        "Sutomo",
        "Gatot Soebroto",
        "Ki Hadjar Dewantarai",
        "Mohammad Hatta",
        "Soedirman",
        "Soekarno",
        "Soepomo",
        "Tan Malaka"
    )

    val listData: ArrayList<ToDo>
        get() {
            val list = arrayListOf<ToDo>()
            for (position in todos.indices) {
                val toDo = ToDo()
                toDo.toDo = todos[position]
                list.add(toDo)
            }
            return list
        }
}