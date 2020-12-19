package com.example.archive.database.dao

import androidx.room.*
import com.example.archive.database.enteties.Cell
import com.example.archive.database.enteties.Request


@Dao
interface RequestDao {
    @Insert
    suspend fun insert(request: Request)

    @Query("SELECT *, COUNT(cell_number) AS amount FROM requests_table GROUP BY cell_number ORDER BY amount DESC LIMIT 1")
    suspend fun getMostOftenCell() : Request?

}