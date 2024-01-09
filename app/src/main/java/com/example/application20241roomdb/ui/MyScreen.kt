package com.example.application20241roomdb.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.application20241roomdb.data.datasource.Note
import com.example.application20241roomdb.util.DISPLAY_TYPE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreen(
    viewModel: MainViewModel= hiltViewModel()
) {
   val uiState =viewModel.uiState
    var menuExpanded by remember{
        mutableStateOf(false)
    }
    var showDialog by remember{
        mutableStateOf(false)
    }
    var note1 by remember {
        mutableStateOf(Note(null,"", "", false))
    }
   Scaffold(
       topBar = {
           TopAppBar(
               title = { Text(text = "NotesApp") },
               actions = {
                   IconButton(onClick = {
                       menuExpanded=!menuExpanded
                   }) {
                       Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                   }
                   DropdownMenu(
                       expanded = menuExpanded,
                       onDismissRequest = { menuExpanded=false }
                   ) {
                       DropdownMenuItem(
                           text = { Text(text = "Toutes") },
                           onClick = {
                               viewModel.switchDisplayType(DISPLAY_TYPE.ALL)
                               menuExpanded =false
                           }
                       )
                       DropdownMenuItem(
                           text = { Text(text = "En cours") },
                           onClick = {
                               viewModel.switchDisplayType(DISPLAY_TYPE.IN_PROGRESS)
                               menuExpanded=false
                           }
                       )
                       DropdownMenuItem(
                           text = { Text(text = "Terminées") },
                           onClick = {
                               viewModel.switchDisplayType(DISPLAY_TYPE.FINISHED)
                               menuExpanded=false
                           }
                       )
                   }
               }
           )
       },
       floatingActionButton = {
        FloatingActionButton(onClick = {
            note1=Note(null,"", "", false)
            showDialog=true
        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Ajout")
        }
        }
   ) {paddingValues -> 
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(paddingValues)

    ){
        LazyColumn(
            modifier=Modifier.fillMaxWidth(),
            contentPadding =  PaddingValues(8.dp)
        ){
            items(uiState.value.currentList){note->
                 NoteItem(
                     note =note,
                     onDoneClick =  {
                         viewModel.update(Note(note.id, note.title, note.description, true))
                     },
                     onUpdate = {
                         showDialog=true
                         note1=note
                                },
                     onDeleteClick = {
                         viewModel.delete(note)
                     }
                 )
            }
        }
       
    }
    if(showDialog){
        UpdateDialog(note = note1, mainViewModel = viewModel, onDismissRequest = {showDialog=false})
    }
       
   } 
}