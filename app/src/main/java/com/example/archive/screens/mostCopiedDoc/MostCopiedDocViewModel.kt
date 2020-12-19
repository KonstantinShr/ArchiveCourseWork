package com.example.archive.screens.mostCopiedDoc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Cell
import kotlinx.coroutines.launch

class MostCopiedDocViewModel(
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

    private var _mostCopiedDocName = MutableLiveData<String>()
    val mostCopiedDocName: LiveData<String>
        get() = _mostCopiedDocName

    init {
        mostCopiedDocName()
    }

    private fun mostCopiedDocName(){
        viewModelScope.launch {
            getMostCopiedDocNameFromDB()
        }
    }

    private suspend fun getMostCopiedDocNameFromDB(){
       _mostCopiedDocName.value = database.cellDao.getMostCopiedDocName()?.docInCell
    }
}