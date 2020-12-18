package com.example.archive.database.dao

import androidx.room.*
import com.example.archive.database.enteties.Cell

@Dao
interface CellDao {
    @Insert
    suspend fun insert(cell: Cell)

    @Update
    suspend fun update(cell: Cell)

    @Delete
    suspend fun delete(cell: Cell)

    @Query("SELECT * FROM cell_table WHERE name_of_doc_in_cell = :docName")
    suspend fun get(docName: String) : Cell?

    @Query("SELECT * FROM cell_table WHERE doc_count = 0 ORDER BY cellId ASC LIMIT 1")
    suspend fun findEmptyCell() : Cell?

}