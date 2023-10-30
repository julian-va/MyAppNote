package com.example.myappnote.ui.view.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myappnote.data.dto.ConstantApp
import com.example.myappnote.ui.viewmodel.note.NoteViewModel


@Composable
fun NoteDetailScreen(noteViewModel: NoteViewModel) {
    noteViewModel.retrieverNoteNameAndNoteDescription()
    var noteName by remember {
        mutableStateOf(noteViewModel.state.value.noteName)
    }
    var noteDescription by remember {
        mutableStateOf(noteViewModel.state.value.noteDescription)
    }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Column {
            CreateOutlinedTextField(
                fieldName = ConstantApp.TITULO,
                text = noteName,
                onValueChange = { noteName = it })
            CreateOutlinedTextField(
                fieldName = ConstantApp.DESCRIPCION,
                text = noteDescription,
                onValueChange = { noteDescription = it })
            Button(onClick = {
                noteViewModel.addOrUpdateNote(
                    noteName = noteName,
                    noteDescription = noteDescription
                )
                noteName = ""
                noteDescription = ""
            }) {
                Text(
                    text = "Save",
                    modifier = Modifier.padding(2.dp),
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOutlinedTextField(
    fieldName: String,
    text: String,
    onValueChange: (value: String) -> Unit
) {
    Text(
        text = fieldName,
        modifier = Modifier.padding(2.dp),
    )
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) }
    )
}