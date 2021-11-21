package com.example.testnoteapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testnoteapp.Note
import com.example.testnoteapp.app.App
import com.example.testnoteapp.dataBase.NoteEntity
import com.example.testnoteapp.dataBase.RoomRepository
import com.example.testnoteapp.dataBase.RoomRepositoryImpl

class MainViewModel : ViewModel() {
    private val roomRepository: RoomRepository = RoomRepositoryImpl(App.getNoteDao())

    private val _data = MutableLiveData<List<Note>>().apply {
        val value: List<Note> = roomRepository.getAllHistory()
    }
    val data: LiveData<List<Note>> = _data
}