package com.example.myappnote.data.dto

import java.util.Date

data class NoteDto(
    val id: Long = System.currentTimeMillis(),
    var noteName: String,
    var noteDescription: String,
    val creationDate: Date = Date(),
    var updateDate: Date?
)
