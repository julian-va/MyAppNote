package com.example.myappnote.ui.view.note


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.myappnote.data.dto.ConstantApp
import com.example.myappnote.data.dto.NoteDto
import com.example.myappnote.ui.viewmodel.note.NoteViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(navController: NavHostController, noteViewModel: NoteViewModel = hiltViewModel()) {

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(
            onClick = { noteViewModel.showDetailScreen() }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "new note")
        }
    }) {
        if (noteViewModel.state.value.showDetailScreen) {
            NoteDetailScreen(noteViewModel = noteViewModel)
        } else {
            if (noteViewModel.state.value.noteList.isNotEmpty()) {
                LazyColumn {
                    items(noteViewModel.state.value.noteList) { notes ->
                        CardNote(
                            noteDto = notes,
                            delete = { noteViewModel.deleteNote(it) },
                            update = { noteViewModel.showDetailScreen(idNOte = it) })
                    }
                }
            } else {
                Text(
                    text = "There are no notes, do you want to add one?",
                    modifier = Modifier.padding(2.dp),
                )
            }
        }
    }
}

@Composable
fun CardNote(noteDto: NoteDto, delete: (note: NoteDto) -> Unit, update: (idNote: Long) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextNote(textHeader = ConstantApp.TITULO, text = noteDto.noteName)
            TextNote(textHeader = ConstantApp.DESCRIPCION, text = noteDto.noteDescription)
            TextNote(
                textHeader = ConstantApp.FECHA_CREACION,
                text = noteDto.creationDate.toString()
            )
            TextNote(
                textHeader = ConstantApp.FECHA_ACTULIZACION,
                text = noteDto.updateDate.toString()
            )
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                IconButton(onClick = { update(noteDto.id) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "actualiza notas")
                }
                IconButton(onClick = { delete(noteDto) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "elimina notas")
                }
            }
        }
    }
}

@Composable
fun TextNote(
    textHeader: String, text: String
) {
    Row {
        Text(
            text = textHeader,
            modifier = Modifier.padding(2.dp),
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(2.dp),
            softWrap = true,
            maxLines = 1
        )
    }
    Spacer(modifier = Modifier.size(size = 2.dp))
}


