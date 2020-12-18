package com.example.archive.database.enteties

import androidx.room.*
import java.util.*


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
        val creationDate: Long = System.currentTimeMillis()
)
