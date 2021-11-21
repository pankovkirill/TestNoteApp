package com.example.testnoteapp.dataBase

import com.example.testnoteapp.Note
import com.example.testnoteapp.utils.convertHistoryEntityToNote
import com.example.testnoteapp.utils.convertListHistoryEntityToNote
import com.example.testnoteapp.utils.convertNoteToEntity

class RoomRepositoryImpl(private val localDataSource: NoteDao) : RoomRepository {
    override fun getAllHistory(): MutableList<Note> {
        return convertListHistoryEntityToNote(localDataSource.all())
    }

    override fun getNoteById(id: Int): Note {
        val entity: NoteEntity = localDataSource.getNoteById(id)
        return convertHistoryEntityToNote(entity)
    }

    override fun deleteNoteById(id: Int) {
        localDataSource.deleteNoteById(id)
    }

    override fun saveEntity(note: Note) {
        localDataSource.insert(convertNoteToEntity(note))
    }
}