package com.example.archive.database.dao

import androidx.room.*
import com.example.archive.database.enteties.Document

@Dao
interface DocumentDao {
    @Insert
    suspend fun insert(document: Document)

    @Update
    suspend fun update(document: Document)

    @Delete
    suspend fun delete(document: Document)

    @Query("SELECT * FROM document_table WHERE name_of_doc = :docName ORDER BY num ASC LIMIT 1")
    suspend fun get(docName: String) : Document?
}