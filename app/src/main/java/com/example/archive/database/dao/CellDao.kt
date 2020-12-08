package com.example.archive.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.archive.database.enteties.Cell

@Dao
interface CellDao {
    @Insert
    suspend fun insert(cell: Cell)

    @Update
    suspend fun update(cell: Cell)

    @Delete
    suspend fun delete(cell: Cell)
}