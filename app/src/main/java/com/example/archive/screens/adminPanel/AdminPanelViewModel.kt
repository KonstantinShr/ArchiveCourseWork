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

    private var _navigateToLoadNewDoc = MutableLiveData<String?>()
    val navigateToLoadNewDoc: LiveData<String?>
        get() = _navigateToLoadNewDoc

    fun onLoadNewDoc(){
        _navigateToLoadNewDoc.value = username
    }

    fun doneNavigateToLoadNewDoc(){
        _navigateToCheckingRequest.value = null
    }

    private var _navigateToChangeDepTelephone = MutableLiveData<String?>()
    val navigateToChangeDepTelephone: LiveData<String?>
        get() = _navigateToChangeDepTelephone

    fun onChangeTelephone(){
        _navigateToChangeDepTelephone.value = username
    }

    fun doneNavigateToChangeDepTelephone(){
        _navigateToChangeDepTelephone.value = null
    }

    private var _navigateToDeleteDocCopy = MutableLiveData<String?>()
    val navigateToDeleteDocCopy: LiveData<String?>
        get() = _navigateToDeleteDocCopy

    fun onDeleteDocCopy(){
        _navigateToDeleteDocCopy.value = username
    }

    fun doneNavigateToDeleteDocCopy(){
        _navigateToDeleteDocCopy.value = null
    }
}