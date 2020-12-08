package com.example.archive.database.enteties

import androidx.room.*
import java.util.*


@Entity(tableName = "cell_table",
        indices = [Index(value = ["name_of_doc_in_cell"], unique = true)],
        foreignKeys = [ForeignKey(entity = Document::class,
                                  parentColumns = ["name_of_doc"],
                                  childColumns = ["name_of_doc_in_cell"]),
                       ForeignKey(entity = User::class,
                                  parentColumns = ["username"],
                                  childColumns = ["who_did_last_request"])])
data class Cell(
        @PrimaryKey(autoGenerate = false)
        val cellId: Int,

        @ColumnInfo(name = "name_of_doc_in_cell")
        var docInCell: String,

        @ColumnInfo(name = "doc_count")
        var docCount: Int = 0,

        @ColumnInfo(name = "creation_date")
        val creationDate: Date = Date(),

        @ColumnInfo(name = "request_date")
        var requestDate: Date = Date(),

        @ColumnInfo(name = "who_did_last_request")
        var lastUser: String,

        @ColumnInfo(name = "count_of_requests")
        var reqCount: Int = 0
)
