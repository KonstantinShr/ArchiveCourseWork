package com.example.archive.screens.checkingRequests

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import kotlinx.coroutines.launch

class CheckingRequestsViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

    init{
        checkPermissions()
    }

    private fun checkPermissions(){
        viewModelScope.launch {
            checkIsAdmin()
        }
    }

    private var _adminPanelBtnVisibility = MutableLiveData<Boolean>()
    val adminPanelBtnVisibility: LiveData<Boolean>
        get() = _adminPanelBtnVisibility

    private suspend fun checkIsAdmin(){
        val user = username?.let { database.userDao.get(username) }
        if (user != null) {
            Log.d("USER'S PERMISSION", user.permissions.toString())
            _adminPanelBtnVisibility.value = user.permissions
        }
    }

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

    private var _navigateToMostOftenDoc = MutableLiveData<String?>()
    val navigateToMostOftenDoc: LiveData<String?>
        get() = _navigateToMostOftenDoc

    fun onMostOftenDoc(){
        _navigateToMostOftenDoc.value = username
    }

    fun doneNavigateToMostOftenDoc(){
        _navigateToMostOftenDoc.value = null
    }

    private var _navigateToDocCountOnTheme = MutableLiveData<String?>()
    val navigateToDocCountOnTheme: LiveData<String?>
        get() = _navigateToDocCountOnTheme

    fun onDocCountOnTheme(){
        _navigateToDocCountOnTheme.value = username
    }

    fun doneNavigateToDocCountOnTheme(){
        _navigateToDocCountOnTheme.value = null
    }

    private var _navigateToMostCopiedDoc = MutableLiveData<String?>()
    val navigateToMostCopiedDoc: LiveData<String?>
        get() = _navigateToMostCopiedDoc

    fun onMostCopiedDoc(){
        _navigateToMostCopiedDoc.value = username
    }

    fun doneNavigateToMostCopiedDoc(){
        _navigateToMostCopiedDoc.value = null
    }

    private var _navigateToMostReqCountDep = MutableLiveData<String?>()
    val navigateToMostReqCountDep: LiveData<String?>
        get() = _navigateToMostReqCountDep

    fun onMostReqCountDep(){
        _navigateToMostReqCountDep.value = username
    }

    fun doneNavigateToMostReqCountDep(){
        _navigateToMostReqCountDep.value = null
    }

    private var _navigateToLastUsernameInDoc = MutableLiveData<String?>()
    val navigateToLastUsernameInDoc: LiveData<String?>
        get() = _navigateToLastUsernameInDoc

    fun onLastUsernameInDoc(){
        _navigateToLastUsernameInDoc.value = username
    }

    fun doneNavigateToLastUsernameInDoc(){
        _navigateToLastUsernameInDoc.value = null
    }

    private var _navigateToThemeOfDoc = MutableLiveData<String?>()
    val navigateToThemeOfDoc: LiveData<String?>
        get() = _navigateToThemeOfDoc

    fun onThemeOfDoc(){
        _navigateToThemeOfDoc.value = username
    }

    fun doneNavigateToThemeOfDoc(){
        _navigateToThemeOfDoc.value = null
    }
}