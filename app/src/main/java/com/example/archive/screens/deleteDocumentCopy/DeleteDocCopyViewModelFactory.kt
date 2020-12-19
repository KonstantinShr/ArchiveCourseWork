package com.example.archive.screens.deleteDocumentCopy

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archive.database.ArchiveDatabase
import com.example.archive.screens.profile.ProfileViewModel

class DeleteDocCopyViewModelFactory(private val username: String?,
                                    private val database: ArchiveDatabase,
                                    private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeleteDocCopyViewModel::class.java)) {
            return DeleteDocCopyViewModel(username, database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}