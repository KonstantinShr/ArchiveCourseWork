package com.example.archive.database.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "documents_themes", indices = [Index(value = ["name_of_the_theme"], unique = true)])
data class DocumentTheme(
    @PrimaryKey(autoGenerate = true)
    val themeId: Long = 0L,

    @ColumnInfo(name = "name_of_the_theme")
    var theme: String = ""
)
