package com.example.archive.database.enteties

import androidx.room.*


@Entity(tableName = "requests_table",
        indices = [Index(value = ["username"], unique = false),
                   Index(value = ["cell_number"], unique = false)],
        foreignKeys = [ForeignKey(entity = User::class,
                                  parentColumns = ["username"],
                                  childColumns = ["username"], onDelete = ForeignKey.CASCADE),
                       ForeignKey(entity = Cell::class,
                                  parentColumns = ["cellId"],
                                  childColumns = ["cell_number"], onDelete = ForeignKey.CASCADE)])
data class Request(
        @PrimaryKey(autoGenerate = true)
        var reqId: Long = 0L,

        @ColumnInfo(name = "username")
        val username: String,

        @ColumnInfo(name = "cell_number")
        val cellId: Long,

        @ColumnInfo(name = "request_date")
        val reqDate: Long = System.currentTimeMillis()
)
