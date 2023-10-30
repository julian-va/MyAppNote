package com.example.myappnote.core.usecase

import com.example.myappnote.data.dto.NoteDto

interface SendNotesService {
    suspend fun sendNotes(notes: List<NoteDto>): List<NoteDto>
}