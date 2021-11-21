package com.example.testnoteapp.dataBase

import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM NoteEntity")
    fun all(): List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id LIKE :id")
    fun getNoteById(id: Int): NoteEntity

    @Query("DELETE FROM NoteEntity WHERE id =:id")
    fun deleteNoteById(id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: NoteEntity)

    @Update
    fun update(entity: NoteEntity)

    @Delete
    fun delete(entity: NoteEntity)
}