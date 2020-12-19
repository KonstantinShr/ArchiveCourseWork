package com.example.archive.screens.changeDepartmentNumber

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Department
import kotlinx.coroutines.launch

class ChangeDepartmentNumberViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application) : AndroidViewModel(application) {

    private var _navigateToAdminPanel = MutableLiveData<String?>()
    val navigateToAdminPanel: LiveData<String?>
        get() = _navigateToAdminPanel

    fun onBack(){
        _navigateToAdminPanel.value = username
    }

    fun doneNavigateToAdminPanel(){
        _navigateToAdminPanel.value = null
    }

    private lateinit var departments: List<Department>

    private var _departmentsString = MutableLiveData<List<String>>()
    val departmentsString: LiveData<List<String>>
        get() = _departmentsString

    init {
        getAllDepartments()
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


    fun changeDepartmentTelephone(depName: String, depTel: String){
        viewModelScope.launch {
            if (depTel.length == 12){
                changeDepartmentTelephoneInDatabase(depName, depTel)
            }
            else{
                Log.d("WRONG NUMBER FORMAT", "НЕПРАВИЛЬНО ВВЕДЕН НОМЕР")
            }
        }
    }

    private suspend fun changeDepartmentTelephoneInDatabase(depName: String, depTel: String){
        val department = database.departmentDao.get(depName)
        if (department == null){
            Log.d("DEPARTMENT NOT FOUND", "ТАКОГО ОТДЕЛА НЕ СУЩЕСТВУЕТ")
        }
        else{
            department.telephone = depTel
            database.departmentDao.update(department)
        }
    }
}