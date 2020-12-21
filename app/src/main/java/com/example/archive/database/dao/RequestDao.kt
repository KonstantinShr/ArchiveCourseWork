package com.example.archive.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.archive.database.enteties.Request


@Dao
interface RequestDao {
    @Insert
    suspend fun insert(request: Request)

    @Query("SELECT *, COUNT(cell_number) AS amount FROM requests_table GROUP BY cell_number ORDER BY amount DESC LIMIT 1")
    suspend fun getMostOftenCell() : Request?

    @Query("SELECT * FROM requests_table WHERE cell_number = :cellId LIMIT 1")
    suspend fun lastReqToCell(cellId: Long) : Request?

}