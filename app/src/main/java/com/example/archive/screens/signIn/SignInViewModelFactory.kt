package com.example.archive.screens.signIn

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archive.database.ArchiveDatabase

class SignInViewModelFactory(
        private val database: ArchiveDatabase,
        private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}