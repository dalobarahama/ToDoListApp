package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ToDoItemBinding
import com.example.todolist.model.ToDo
import com.example.todolist.utils.ToDoClickListener

class ToDoAdapter(val todoList: ArrayList<ToDo>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoHolder>(), ToDoClickListener {

    fun updateToDoList(newToDoList: List<ToDo>) {
        todoList.clear()
        todoList.addAll(newToDoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ToDoItemBinding>(inflater, R.layout.to_do_item, parent, false)
        return ToDoHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        holder.view.todo = todoList[position]
    }

    class ToDoHolder(var view: ToDoItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onToDoClicked(view: View) {
        Toast.makeText(view.context, "Clicked", Toast.LENGTH_SHORT).show()
    }


}