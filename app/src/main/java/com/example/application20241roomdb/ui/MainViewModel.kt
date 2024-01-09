package com.example.application20241roomdb.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.application20241roomdb.data.datasource.Note
import com.example.application20241roomdb.data.repository.NoteRepository
import com.example.application20241roomdb.ui.UiState
import com.example.application20241roomdb.util.DISPLAY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private  val repository: NoteRepository) :ViewModel(){
    val uiState= mutableStateOf(UiState())

    init {
        getNotes()
    }

    fun getNotes(){
        when(uiState.value.displayType){
            DISPLAY_TYPE.ALL->{
                viewModelScope.launch {
                    repository.getAll().collect{noteList->
                        uiState.value=uiState.value.copy(currentList =noteList)

                    }
                }
            }
            DISPLAY_TYPE.IN_PROGRESS->{
                viewModelScope.launch {
                    repository.getFiltered(isFinished = false).collect{noteList->
                        uiState.value=uiState.value.copy(currentList =noteList)

                    }
                }
            }
            DISPLAY_TYPE.FINISHED->{
                viewModelScope.launch {
                    repository.getFiltered(isFinished = true).collect{noteList->
                        uiState.value=uiState.value.copy(currentList =noteList)

                    }
                }
            }


            else -> {}
        }
    }

    fun insert(note: Note){
        viewModelScope.launch {
            repository.insert(note)
        }
    }
    fun delete(note: Note){
        viewModelScope.launch {
            repository.delete(note)
        }
    }
    fun update(note: Note){
        viewModelScope.launch {
            repository.update(note)
        }
    }
    fun switchDisplayType(dt:DISPLAY_TYPE){
        uiState.value =uiState.value.copy(displayType = dt)
        getNotes()
    }
}