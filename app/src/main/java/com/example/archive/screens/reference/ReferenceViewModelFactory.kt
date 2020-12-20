package com.example.archive.screens.reference

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archive.database.ArchiveDatabase
import com.example.archive.screens.choseDepForReference.ChoseDepForReferenceViewModel

class ReferenceViewModelFactory(private val username: String?,
                                private val depName: String?,
                                private val database: ArchiveDatabase,
                                private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReferenceViewModel::class.java)) {
            return ReferenceViewModel(username, depName, database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}