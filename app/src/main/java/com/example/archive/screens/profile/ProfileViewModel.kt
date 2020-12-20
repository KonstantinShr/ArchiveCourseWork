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
            getUser()
        }
    }

    private var _userInfo = MutableLiveData<String?>()
    val userInfo: LiveData<String?>
        get() = _userInfo

    private suspend fun getUser() {
        val user = username?.let { database.userDao.get(username) }
        val perm: String

        if (user?.permissions == true){
            perm = "Является администратором"
        }
        else{
            perm = "Не является администратором"
        }

        _userInfo.value = "Данный аккаунт принадлежит следующему пользователю:\n\nФИО: ${user?.realName}\n" +
                "username: ${user?.username}\nРаботает в следующем отделе: ${user?.depName}\n\n" + perm
    }

}