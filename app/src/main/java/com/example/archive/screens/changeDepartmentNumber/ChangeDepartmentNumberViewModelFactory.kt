package com.example.archive.screens.changeDepartmentNumber

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archive.database.ArchiveDatabase

class ChangeDepartmentNumberViewModelFactory(private val username: String?,
                                             private val database: ArchiveDatabase,
                                             private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChangeDepartmentNumberViewModel::class.java)) {
            return ChangeDepartmentNumberViewModel(username, database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}