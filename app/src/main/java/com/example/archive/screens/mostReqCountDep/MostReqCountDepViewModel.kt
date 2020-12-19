package com.example.archive.screens.mostReqCountDep

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Cell
import com.example.archive.database.enteties.Department
import kotlinx.coroutines.launch

class MostReqCountDepViewModel(
    val username: String?,
    val database: ArchiveDatabase,
    application: Application
): AndroidViewModel(application) {

    private var _navigateToMain = MutableLiveData<String?>()
    val navigateToMain: LiveData<String?>
        get() = _navigateToMain

    fun onBack(){
        _navigateToMain.value = username
    }

    fun doneNavigateToMain(){
        _navigateToMain.value = null
    }

    private var _mostReqCountDep = MutableLiveData<String>()
    val mostReqCountDep: LiveData<String>
        get() = _mostReqCountDep

    init {
        mostReqCountDep()
    }

    private fun mostReqCountDep(){
        viewModelScope.launch {
            getMostReqCountDepFromDB()
        }
    }

    private suspend fun getMostReqCountDepFromDB(){
       _mostReqCountDep.value = database.departmentDao.getMostReqCountDep()?.depName
    }
}