package com.example.archive.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.archive.database.ArchiveDatabase

class MainViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

    private var _navigateToProfile = MutableLiveData<String?>()
    val navigateToProfile: LiveData<String?>
        get() = _navigateToProfile

    fun onProfile(){
        _navigateToProfile.value = username
    }

    fun doneNavigateToProfile(){
        _navigateToProfile.value = null
    }

    private var _navigateToCheckingReq = MutableLiveData<String?>()
    val navigateToCheckingReq: LiveData<String?>
        get() = _navigateToCheckingReq

    fun onCheckingReq(){
        _navigateToCheckingReq.value = username
    }

    fun doneNavigateToCheckingReq(){
        _navigateToCheckingReq.value = null
    }

    private var _navigateToGetReference = MutableLiveData<String?>()
    val navigateToGetReference: LiveData<String?>
        get() = _navigateToGetReference

    fun onGetReference(){
        _navigateToGetReference.value = username
    }

    fun doneNavigateToGetReference(){
        _navigateToGetReference.value = null
    }

    private var _navigateToGetReport = MutableLiveData<String?>()
    val navigateToGetReport: LiveData<String?>
        get() = _navigateToGetReport

    fun onGetReport(){
        _navigateToGetReport.value = username
    }

    fun doneNavigateToGetReport(){
        _navigateToGetReport.value = null
    }
}