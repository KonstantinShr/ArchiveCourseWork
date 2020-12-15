package com.example.archive.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.archive.database.dao.*
import com.example.archive.database.enteties.*

@Database(entities = [Cell::class,
                      Department::class,
                      Document::class,
                      DocumentTheme::class,
                      User::class,
                      Request::class], version = 3, exportSchema = false)
abstract class ArchiveDatabase : RoomDatabase() {

    abstract val cellDao: CellDao

    abstract val departmentDao: DepartmentDao

    abstract val documentDao: DocumentDao

    abstract val documentThemeDao: DocumentThemeDao

    abstract val userDao: UserDao

    abstract val requestDao: RequestDao

    companion object{
        /**
         * INSTANCE will keep a reference to any database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */
        @Volatile
        private var INSTANCE: ArchiveDatabase? = null

        fun getInstance(context: Context) : ArchiveDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                                                    ArchiveDatabase::class.java,
                                                    "archive_database")
                                    .fallbackToDestructiveMigration()
                                    .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}