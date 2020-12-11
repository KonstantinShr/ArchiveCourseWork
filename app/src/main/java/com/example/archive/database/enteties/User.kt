package com.example.archive.database.enteties

import androidx.room.*


@Entity(tableName = "user_table",
        foreignKeys = [ForeignKey(entity = Department::class,
                                  parentColumns = ["name_of_department"],
                                  childColumns = ["department"])],
        indices = [Index(value = ["username"], unique = true), Index(value = ["department"])])
data class User(
        @PrimaryKey(autoGenerate = false)
        val username: String,

        @ColumnInfo(name = "name")
        val realName: String,

        @ColumnInfo(name = "department")
        val depName: String,

        @ColumnInfo(name = "permissions")
        val permissions: Boolean = false
)
