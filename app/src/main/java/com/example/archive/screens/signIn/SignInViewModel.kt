package com.example.archive.screens.signIn

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

class SignInViewModel(
        val database: ArchiveDatabase,
        application: Application

): AndroidViewModel(application) {

    private var username: String? = null

    private var _navigateToMain = MutableLiveData<String?>()
    val navigateToMain: LiveData<String?>
        get() = _navigateToMain

    init {
        fillingDepartmentTable()
    }

    fun updateUsername(un: String){
        username = un
        viewModelScope.launch {
            getUserOrNull(username)
        }

    }

    private suspend fun getUserOrNull(un: String?){
        val user: User? = un?.let { database.userDao.get(it) }
        if (user == null){
            Log.d("USERINFO", "user not found")
        }
        else{
            _navigateToMain.value = user.username
        }
    }

    fun doneNavigateToMain(){
        _navigateToMain.value = null
    }

    private fun fillingDepartmentTable(){
        viewModelScope.launch {
            val departments = getAllDepartment()

            if (departments.isEmpty()){
                fillDepartment()
            }
        }
    }

    private suspend fun getAllDepartment() : List<Department>{
        return database.departmentDao.getAllDepartment()
    }

    private suspend fun fillDepartment(){
        val departments: List<Department> = listOf(Department(depName = "Бухгалтерия", telephone = "+11111111111"),
                Department(depName = "Отдел кадров", telephone = "+22222222222"),
                Department(depName = "Отдел перевозок", telephone = "+33333333333"),
                Department(depName = "Отдел маркетинга", telephone = "+44444444444"),
                Department(depName = "Руководящий отдел", telephone = "+55555555555")
        )
        for (department in departments){
            database.departmentDao.insert(department)
        }
    }


}