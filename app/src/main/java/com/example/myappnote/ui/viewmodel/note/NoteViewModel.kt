package com.example.myappnote.ui.viewmodel.note

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.myappnote.data.dto.NoteDto
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor() : ViewModel() {
    val ZERO = 0L
    private val _state = mutableStateOf(NoteViewModelState())
    val state = _state

    init {
        _state.value = _state.value.copy(
            noteList = mutableStateListOf(
                NoteDto(
                    noteName = "NOTA NAME 1",
                    noteDescription = "NOTE DESCRIPTION 1",
                    updateDate = null
                ),
                NoteDto(
                    noteName = "NOTA NAME 2",
                    noteDescription = "NOTE DESCRIPTION 2",
                    updateDate = null
                ),
                NoteDto(
                    noteName = "NOTA NAME 3",
                    noteDescription = "NOTE DESCRIPTION 3",
                    updateDate = null
                )
            )
        )
    }


    fun deleteNote(noteDto: NoteDto) {
        val noteLIst = state.value.noteList
        noteLIst.remove(noteDto)
        updateState(noteLIst = noteLIst, showDetailScreen = false)
    }

    fun addOrUpdateNote(noteName: String, noteDescription: String) {
        val idNote = state.value.idNote
        if (ZERO.equals(idNote)) addNote(
            noteName = noteName,
            noteDescription = noteDescription
        ) else updateNote(idNote = idNote, noteName = noteName, noteDescription = noteDescription)
    }

    private fun addNote(noteName: String, noteDescription: String) {
        val temp =
            NoteDto(noteName = noteName, noteDescription = noteDescription, updateDate = null)
        val noteLIst = state.value.noteList
        noteLIst.add(temp)
        updateState(noteLIst = noteLIst, showDetailScreen = false)
    }

    private fun updateNote(idNote: Long, noteName: String, noteDescription: String) {
        val noteLIst = state.value.noteList
        val index = noteLIst.indexOfFirst { noteDto -> idNote.equals(noteDto.id) }
        noteLIst.find { noteDto -> idNote.equals(noteDto.id) }?.let { noteDto ->
            noteDto.noteName = if (noteName.isNotBlank()) noteName else noteDto.noteName
            noteDto.noteDescription =
                if (noteDescription.isNotBlank()) noteName else noteDto.noteDescription
            noteDto.updateDate = Date()
            noteLIst[index] = noteDto
        }
        updateState(noteLIst = noteLIst, showDetailScreen = false)
    }

    fun showDetailScreen(idNOte: Long = 0) {
        _state.value = _state.value.copy(
            showDetailScreen = true,
            idNote = if (ZERO.equals(idNOte)) state.value.idNote else idNOte
        )
    }

    private fun updateState(
        noteLIst: SnapshotStateList<NoteDto> = mutableStateListOf(),
        showDetailScreen: Boolean
    ) {
        _state.value = _state.value.copy(
            noteList = if (noteLIst.isNotEmpty()) noteLIst else state.value.noteList,
            showDetailScreen = showDetailScreen,
            idNote = ZERO
        )
    }
}