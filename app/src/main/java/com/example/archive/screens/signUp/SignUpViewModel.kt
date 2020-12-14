package com.example.archive.screens.signUp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Department
import com.example.archive.database.enteties.User
import kotlinx.coroutines.launch

class SignUpViewModel(
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

        private var _navigateToMain = MutableLiveData<String?>()
        val navigateToMain: LiveData<String?>
            get() = _navigateToMain


        private lateinit var departments: List<Department>

        private var _departmentsString = MutableLiveData<List<String>>()
        val departmentsString: LiveData<List<String>>
            get() = _departmentsString

        init {
            getAllDepartments()
        }

        fun registration(un: String, realName: String, department: String, perm: Boolean){
            viewModelScope.launch {
                val newUser = User(un, realName, department, perm)

                registrate(newUser)
                    
                _navigateToMain.value = newUser.username
                Log.d("REGISTRATION", "${newUser.username} ${newUser.realName} ${newUser.depName} ${newUser.permissions}")
                }
            }

        private suspend fun registrate(user: User){
            database.userDao.insert(user)
        }

        fun doneNavigateToMain(){
            _navigateToMain.value = null
        }

        private var depStrings: MutableList<String> = mutableListOf()

        private fun getAllDepartments(){
            viewModelScope.launch {
                departments = getDepartmentsFromDatabase()
                for (i in departments.indices){
                    depStrings.add(departments[i].depName)
                }
                _departmentsString.value = depStrings
            }
        }

        private suspend fun getDepartmentsFromDatabase(): List<Department>{
            return database.departmentDao.getAllDepartment()
        }
}