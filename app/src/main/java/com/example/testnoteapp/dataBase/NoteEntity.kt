package com.example.testnoteapp.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val date: String
)