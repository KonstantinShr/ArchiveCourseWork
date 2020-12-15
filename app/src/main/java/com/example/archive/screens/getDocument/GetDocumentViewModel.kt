package com.example.archive.screens.getDocument

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Cell
import com.example.archive.database.enteties.Request
import kotlinx.coroutines.launch

class GetDocumentViewModel(
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

    fun createRequest(dn: String){
        viewModelScope.launch {
            val cell = getDocOrNull(dn)
            if (cell == null){
                Log.d("GET DOCUMENT", " DOCUMENT NOT FOUND")
            }
            else{
                val request = username?.let { Request(username = username, cellId = cell.cellId) }
                if (request != null) {
                    doRequest(request)
                }
            }
        }
    }

    private suspend fun getDocOrNull(docName: String) : Cell?{
        return database.cellDao.get(docName)
    }

    private suspend fun doRequest(request: Request){
        database.requestDao.insert(request)
        incDepartmentRequestCount()
    }

    private suspend fun incDepartmentRequestCount(){
        val user = username?.let { database.userDao.get(username) }
        val department = user?.let { database.departmentDao.get(user.depName) }
        if (department != null) {
            department.reqCount += 1
        }
    }
}