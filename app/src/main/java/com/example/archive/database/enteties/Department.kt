package com.example.archive.database.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "departments", indices = [Index(value = ["name_of_department"], unique = true),
                                              Index(value =  ["tel"], unique = true)])
data class Department(
        @PrimaryKey(autoGenerate = true)
        val depId: Long = 0L,

        @ColumnInfo(name = "name_of_department")
        val depName: String,

        @ColumnInfo(name = "tel")
        var telephone: String,

        @ColumnInfo(name = "requests_count")
        var reqCount: Int = 0
)
