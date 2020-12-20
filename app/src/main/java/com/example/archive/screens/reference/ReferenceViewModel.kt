package com.example.archive.screens.reference

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import kotlinx.coroutines.launch

class ReferenceViewModel(
    val username: String?,
    val depName: String?,
    val database: ArchiveDatabase,
    application: Application
): AndroidViewModel(application) {

    init {
        getAllDepUsers()
    }



    private fun getAllDepUsers(){
        viewModelScope.launch {
            getAllDepUsersFromDB()
        }
    }

    private var _reference = MutableLiveData<String>()
    val reference : LiveData<String>
        get() = _reference

    private suspend fun getAllDepUsersFromDB(){
        val users = depName?.let { database.userDao.getAllUsersOfDep(depName) }

        var finalString = "Выбранный отдел: $depName \n\nВсего пользователей из данного отдела: ${users?.size}\n\nИнформация о пользователях архива из выбранного отдела:\n\n"

        if (users != null) {
            for (user in users){
                finalString += "ФИО: ${user.realName}\nusername: ${user.username}\n"
                if (user.permissions){
                    finalString += "Является администратором\n\n"
                }
                else{
                    finalString += "Не является администратором\n\n"
                }
            }
        }

        _reference.value = finalString
    }

    private var _navigateToChooseDep = MutableLiveData<String?>()
    val navigateToChooseDep: LiveData<String?>
        get() = _navigateToChooseDep

    fun onBack(){
        _navigateToChooseDep.value = username
    }

    fun doneNavigateToChooseDep(){
        _navigateToChooseDep.value = null
    }
}