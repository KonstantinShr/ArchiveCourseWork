package com.example.archive.database.dao

import androidx.room.*
import com.example.archive.database.enteties.DocumentTheme

@Dao
interface DocumentThemeDao {
    @Insert
    suspend fun insert(documentTheme: DocumentTheme)

    @Update
    suspend fun update(documentTheme: DocumentTheme)

    @Delete
    suspend fun delete(documentTheme: DocumentTheme)

    @Query("SELECT * FROM documents_themes")
    suspend fun getAllTheme() : List<DocumentTheme>
}