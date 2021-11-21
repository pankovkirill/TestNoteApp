package com.example.testnoteapp.utils

import com.example.testnoteapp.Note
import com.example.testnoteapp.dataBase.NoteEntity

fun convertListHistoryEntityToNote(entityList: List<NoteEntity>): MutableList<Note> {
    return entityList.map {
        Note(it.id, it.title, it.content, it.date)
    }.toMutableList()
}

fun convertHistoryEntityToNote(entityList: NoteEntity): Note {
    return Note(entityList.id, entityList.title, entityList.content, entityList.date)

}

fun convertNoteToEntity(note: Note): NoteEntity {
    with(note) {
        return NoteEntity(
            0,
            title,
            content,
            date
        )
    }
}