package com.example.application20241roomdb.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDAO {
    @Insert
    suspend fun insertNote(note: Note)
    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * from notes")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * from notes where isFinished= :isFinished")
    fun getFilteredNotes(isFinished:Boolean):Flow<List<Note>>

    @Query("SELECT * from notes where id = :id")
    fun getNote(id:Int):Flow<Note>
}