package com.example.archive.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.archive.database.enteties.DocumentTheme

@Dao
interface DocumentThemeDao {
    @Insert
    suspend fun insert(documentTheme: DocumentTheme)

    @Update
    suspend fun update(documentTheme: DocumentTheme)

    @Delete
    suspend fun delete(documentTheme: DocumentTheme)
}