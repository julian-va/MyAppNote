package com.example.myappnote.ui.viewmodel.note

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.myappnote.data.dto.NoteDto

data class NoteViewModelState(
    val noteList: SnapshotStateList<NoteDto> = mutableStateListOf(),
    val showDetailScreen: Boolean = false,
    val idNote: Long = 0
)
