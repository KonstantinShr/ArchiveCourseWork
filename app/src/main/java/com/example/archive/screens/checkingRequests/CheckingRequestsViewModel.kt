package com.example.archive.screens.checkingRequests

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.archive.database.ArchiveDatabase

class CheckingRequestsViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {
}