package com.example.archive.screens.mostReqCountDep

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archive.database.ArchiveDatabase
import com.example.archive.screens.mostOftenDoc.MostOftenDocViewModel

class MostReqCountDepViewModelFactory(private val username: String?,
                                      private val database: ArchiveDatabase,
                                      private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MostReqCountDepViewModel::class.java)) {
            return MostReqCountDepViewModel(username, database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}