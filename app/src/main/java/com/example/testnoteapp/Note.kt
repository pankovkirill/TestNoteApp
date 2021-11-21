package com.example.testnoteapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    val id: Int = 0,
    val title: String = "Title",
    val content: String = "Content",
    val date: String = "19 ноября 2021"
) : Parcelable