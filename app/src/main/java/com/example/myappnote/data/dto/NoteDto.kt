package com.example.myappnote.data.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myappnote.util.UtilsApp

data class NoteDto @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: Long = System.currentTimeMillis(),
    var noteName: String,
    var noteDescription: String,
    val creationDate: String = UtilsApp.parseDatePatter(),
    var updateDate: String?
)
