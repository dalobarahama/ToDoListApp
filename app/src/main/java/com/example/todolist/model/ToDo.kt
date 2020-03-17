package com.example.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    @ColumnInfo(name = "to_do")
    var toDo: String? = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

