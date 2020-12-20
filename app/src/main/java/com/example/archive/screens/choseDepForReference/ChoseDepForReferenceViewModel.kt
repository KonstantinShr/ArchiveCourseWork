package com.example.archive.screens.choseDepForReference

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Department
import kotlinx.coroutines.launch

class ChoseDepForReferenceViewModel(
    val username: String?,
    val database: ArchiveDatabase,
    application: Application
) : AndroidViewModel(application) {

    private var _navigateToMain = MutableLiveData<String?>()
    val navigateToMain: LiveData<String?>
        get() = _navigateToMain

    fun onBack(){
        _navigateToMain.value = username
    }

    fun doneNavigateToMain(){
        _navigateToMain.value = null
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


    private var _navigateToReference = MutableLiveData<List<String?>>()
    val navigateToReference: LiveData<List<String?>>
        get() = _navigateToReference

    fun getReference(depName: String){
        _navigateToReference.value = listOf(username, depName)
    }

}