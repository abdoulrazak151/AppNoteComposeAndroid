package com.example.application20241roomdb.data.repository

import com.example.application20241roomdb.data.datasource.Note
import com.example.application20241roomdb.data.datasource.NoteDAO
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val  dao:NoteDAO
) {
    suspend fun insert(note: Note)=dao.insertNote(note)
    suspend fun delete(note: Note)=dao.deleteNote(note)
    suspend fun update(note: Note)=dao.updateNote(note)
    fun getAll()=dao.getAllNotes();
    fun getFiltered(isFinished:Boolean)=dao.getFilteredNotes(isFinished)
    fun getNote(id:Int)=dao.getNote(id)
}