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
}