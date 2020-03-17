package com.example.todolist.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
//    @Insert
//    suspend fun insert(toDo: ToDo): ToDo

    @Insert
    suspend fun insertAll(vararg todo: ToDo): List<Long>

    @Query("SELECT * FROM todo")
    suspend fun getAllToDoList(): List<ToDo>

    @Query("SELECT * FROM todo WHERE id = :todoID")
    suspend fun getToDo(todoID: Int): ToDo

//    @Query("DELETE FROM todo WHERE id = :todoID")
//    suspend fun deleteToDo(todoID: Int): ToDo
}