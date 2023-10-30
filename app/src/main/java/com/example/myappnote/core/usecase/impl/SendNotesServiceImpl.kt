package com.example.myappnote.core.usecase.impl

import com.example.myappnote.core.usecase.SendNotesService
import com.example.myappnote.data.dto.NoteDto
import com.example.myappnote.data.repository.network.NoteRepository
import javax.inject.Inject

class SendNotesServiceImpl @Inject constructor(private val noteRepository: NoteRepository) :
    SendNotesService {
    override suspend fun sendNotes(notes: List<NoteDto>): List<NoteDto> {
        var notesBody: List<NoteDto> = emptyList()
        try {
            val response = noteRepository.sendNotes(notes = notes)
            if (response.isSuccessful) {
                response.body()?.let { noteDtos -> notesBody = noteDtos }
            }
        } catch (e: Exception) {
            println("failed to send the notes to the server, the error is: ${e.message}")
        }

        return notesBody
    }
}