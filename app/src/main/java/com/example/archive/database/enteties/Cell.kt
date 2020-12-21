package com.example.archive.database.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "cell_table",
        indices = [Index(value = ["name_of_doc_in_cell"], unique = true)])
data class Cell(
        @PrimaryKey(autoGenerate = true)
        var cellId: Long = 0L,

        @ColumnInfo(name = "name_of_doc_in_cell")
        var docInCell: String,

        @ColumnInfo(name = "doc_count")
        var docCount: Int = 0,

        @ColumnInfo(name = "creation_date")
        var creationDate: Long = System.currentTimeMillis()
)
