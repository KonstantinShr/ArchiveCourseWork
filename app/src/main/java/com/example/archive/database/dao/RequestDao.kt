package com.example.archive.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.archive.database.enteties.Cell
import com.example.archive.database.enteties.Request


@Dao
interface RequestDao {
    @Insert
    suspend fun insert(request: Request)

}