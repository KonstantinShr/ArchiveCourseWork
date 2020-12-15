package com.example.archive.screens.adminPanel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.archive.database.ArchiveDatabase

class AdminPanelViewModel(val username: String?,
    val database: ArchiveDatabase,
    application: Application): AndroidViewModel(application) {

    private var _navigateToCheckingRequest = MutableLiveData<String?>()
    val navigateToCheckingRequest: LiveData<String?>
        get() = _navigateToCheckingRequest

    fun onBack(){
        _navigateToCheckingRequest.value = username
    }

    fun doneNavigateToCheckingRequest(){
        _navigateToCheckingRequest.value = null
    }
}