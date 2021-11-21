package com.example.testnoteapp.dataBase

import androidx.room.Query
import androidx.room.Update
import com.example.testnoteapp.Note

interface RoomRepository {
    fun getAllHistory(): MutableList<Note>
    fun getNoteById(id: Int): Note
    fun deleteNoteById(id: Int)
    fun saveEntity(note: Note)
}