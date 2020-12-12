package com.example.archive.screens.signUp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.User

class SignUpViewModel(
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

        private var _navigateToMain = MutableLiveData<String?>()

        val navigateToMain: LiveData<String?>
            get() = _navigateToMain

            fun registration(un: String, realName: String, perm: Boolean){
                val newUser = User(un, realName, "sdvs", perm)

                //вставить регистрацию пользователя в бд
                _navigateToMain.value = newUser.username
                Log.d("REGISTRATION", "${newUser.username} ${newUser.realName} ${newUser.permissions}")
            }

        fun doneNavigateToMain(){
            _navigateToMain.value = null
        }
}