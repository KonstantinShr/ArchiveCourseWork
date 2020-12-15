package com.example.archive.screens.checkingRequests

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.archive.database.ArchiveDatabase

class CheckingRequestsViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

    private var _navigateToMain = MutableLiveData<String?>()
    val navigateToMain: LiveData<String?>
        get() = _navigateToMain

    fun onBack(){
        _navigateToMain.value = username
    }

    fun doneNavigateToMain(){
        _navigateToMain.value = null
    }

    private var _navigateToAdminPanel = MutableLiveData<String?>()
    val navigateToAdminPanel: LiveData<String?>
        get() = _navigateToAdminPanel

    fun onAdminPanel(){
        _navigateToAdminPanel.value = username
    }

    fun doneNavigateToAdminPanel(){
        _navigateToAdminPanel.value = null
    }
}