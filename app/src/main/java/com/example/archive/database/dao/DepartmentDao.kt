package com.example.archive.database.dao

import androidx.lifecycle.MutableLiveData
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

    @Query("SELECT * FROM departments_table WHERE name_of_department = :department")
    suspend fun get(department: String) : Department?

    @Query("SELECT * FROM departments_table ORDER BY requests_count DESC LIMIT 1")
    suspend fun getMostReqCountDep() : Department?
}