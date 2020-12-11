package com.example.archive.screens.signIn

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.User
import kotlinx.coroutines.launch

class SignInViewModel(
        val database: ArchiveDatabase,
        application: Application

): AndroidViewModel(application) {

    private var username: String = ""

    private var _user = MutableLiveData<User?>()
    val user: LiveData<User?>
        get() = _user

    private var _navigateToMainEvent = MutableLiveData<Boolean>()
    val navigateToMainEvent: LiveData<Boolean>
        get() = _navigateToMainEvent

    fun updateUsername(un: String){
        username = un
        Log.d("username_update", username)

        viewModelScope.launch {
            getUserOrNull(username)
        }

    }

    suspend fun getUserOrNull(un: String){
        val user: User? = database.userDao.get(un)
        if (user == null){
            Log.d("USERINFO", "user not found")
        }
        else{
            _navigateToMainEvent.value = true
        }
    }

    fun doneNavigateToMain(){
        _navigateToMainEvent.value = false
    }


}