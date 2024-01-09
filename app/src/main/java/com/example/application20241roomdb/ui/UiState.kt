package com.example.application20241roomdb.ui

import com.example.application20241roomdb.data.datasource.Note
import com.example.application20241roomdb.util.DISPLAY_TYPE

data class UiState(
    val displayType: DISPLAY_TYPE=DISPLAY_TYPE.ALL,
    val currentList:List<Note> = emptyList()
)
