package com.example.archive.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.archive.database.enteties.Document

@Dao
interface DocumentDao {
    @Insert
    suspend fun insert(document: Document)

    @Update
    suspend fun update(document: Document)

    @Delete
    suspend fun delete(document: Document)
}