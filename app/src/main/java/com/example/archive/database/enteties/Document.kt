package com.example.archive.database.enteties

import androidx.room.*
import androidx.room.ForeignKey.CASCADE


@Entity(tableName = "document_table",
        foreignKeys = [ForeignKey(entity = DocumentTheme::class,
                                  parentColumns = ["name_of_the_theme"],
                                  childColumns = ["theme_of_doc"], onDelete = CASCADE),
                       ForeignKey(entity = Cell::class,
                                  parentColumns = ["name_of_doc_in_cell"],
                                  childColumns = ["name_of_doc"], onDelete = CASCADE)],
        indices = [//Index(value = ["num"], unique = true),
                   Index(value =  ["name_of_doc"]),
                   Index(value = ["theme_of_doc"])])
data class Document(
        @PrimaryKey(autoGenerate = true)
        var num: Long = 0L,

        @ColumnInfo(name = "name_of_doc")
        val nameDoc: String,

        @ColumnInfo(name = "theme_of_doc")
        val docTheme: String
)
