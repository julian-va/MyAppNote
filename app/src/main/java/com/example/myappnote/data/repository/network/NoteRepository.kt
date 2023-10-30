package com.example.myappnote.data.repository.network

import com.example.myappnote.data.dto.NoteDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NoteRepository {
    @POST(value = "note")
    suspend fun sendNotes(@Body notes: List<NoteDto>): Response<List<NoteDto>>
}