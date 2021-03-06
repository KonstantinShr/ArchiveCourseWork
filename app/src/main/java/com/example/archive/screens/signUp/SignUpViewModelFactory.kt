package com.example.archive.screens.signUp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.archive.database.ArchiveDatabase
import com.example.archive.screens.signIn.SignInViewModel

class SignUpViewModelFactory (
        private val database: ArchiveDatabase,
        private val application: Application
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}