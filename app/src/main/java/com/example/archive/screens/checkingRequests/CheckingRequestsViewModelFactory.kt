package com.example.archive.screens.checkingRequests

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archive.database.ArchiveDatabase

class CheckingRequestsViewModelFactory(private val username: String?,
                                       private val database: ArchiveDatabase,
                                       private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckingRequestsViewModel::class.java)) {
            return CheckingRequestsViewModel(username, database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}