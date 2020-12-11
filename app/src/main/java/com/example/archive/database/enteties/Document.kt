package com.example.archive.database.enteties

import androidx.room.*


@Entity(tableName = "document_table",
        foreignKeys = [ForeignKey(entity = DocumentTheme::class,
                                  parentColumns = ["name_of_the_theme"],
                                  childColumns = ["theme_of_doc"])],
        indices = [Index(value = ["num"], unique = true),
                   Index(value =  ["name_of_doc"], unique = true),
                   Index(value = ["theme_of_doc"])])
data class Document(
        @PrimaryKey(autoGenerate = false)
        val num: String,

        @ColumnInfo(name = "name_of_doc")
        val nameDoc: String,

        @ColumnInfo(name = "theme_of_doc")
        val docTheme: String
)
