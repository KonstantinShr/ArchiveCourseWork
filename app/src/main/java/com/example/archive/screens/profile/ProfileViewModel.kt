package com.example.archive.screens.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.User
import kotlinx.coroutines.launch

class ProfileViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

    private var _navigateToMain = MutableLiveData<String?>()
    val navigateToMain: LiveData<String?>
        get() = _navigateToMain

    fun onBack(){
        _navigateToMain.value = username
        Log.d("USER'S PROFILE", user.toString())
    }

    fun doneNavigateToMain(){
        _navigateToMain.value = null
    }

    private var user: User? = null

    init {
        getUserProfile()
    }

    private fun getUserProfile(){
        viewModelScope.launch {
            user = getUser()
        }
    }

    private suspend fun getUser(): User? {
        return username?.let { database.userDao.get(username) }
    }

}