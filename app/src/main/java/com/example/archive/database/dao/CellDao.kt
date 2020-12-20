package com.example.archive.database.dao

import androidx.lifecycle.MutableLiveData
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

    @Query("SELECT * FROM cell_table ORDER BY doc_count DESC LIMIT 1")
    suspend fun getMostCopiedDocName() : Cell?

    @Query("SELECT * FROM cell_table WHERE cellId = :cellId")
    suspend fun getByCellId(cellId: Long): Cell?

    @Query("SELECT * FROM cell_table")
    suspend fun getAllCell() : List<Cell>?

    @Query("SELECT * FROM cell_table WHERE (:currentTime - creation_date) < 2629800000")
    suspend fun lastMonthDocs(currentTime: Long): List<Cell>?

}