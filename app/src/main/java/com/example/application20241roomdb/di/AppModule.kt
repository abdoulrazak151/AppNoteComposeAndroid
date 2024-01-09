package com.example.application20241roomdb.di

import android.content.Context
import androidx.room.Room
import com.example.application20241roomdb.data.datasource.NoteDAO
import com.example.application20241roomdb.data.datasource.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):NoteDatabase{
        return Room.databaseBuilder(
            context = context.applicationContext,
            NoteDatabase::class.java,
            "Notes.db"
        ).build()
    }
    @Provides
    fun providesNoteDao(database: NoteDatabase):NoteDAO =database.noteDAO()

}