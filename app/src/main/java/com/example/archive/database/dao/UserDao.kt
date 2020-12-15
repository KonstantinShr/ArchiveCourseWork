package com.example.archive.database.dao

import androidx.room.*
import com.example.archive.database.enteties.Department
import com.example.archive.database.enteties.User


@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user_table WHERE username = :username")
    suspend fun get(username: String) : User?

}