package com.example.archive.database.dao

import androidx.room.*
import com.example.archive.database.enteties.Department


@Dao
interface DepartmentDao {
    @Insert
    suspend fun insert(department: Department)

    @Update
    suspend fun update(department: Department)

    @Delete
    suspend fun delete(department: Department)

    @Query("SELECT * FROM departments_table")
    suspend fun getAllDepartment() : List<Department>
}