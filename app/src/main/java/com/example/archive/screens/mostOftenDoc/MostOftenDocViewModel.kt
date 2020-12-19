package com.example.archive.screens.mostOftenDoc

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Cell
import kotlinx.coroutines.launch

class MostOftenDocViewModel(
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

    private var _mostOftenDocName = MutableLiveData<String?>()
    val mostOftenDocName: LiveData<String?>
        get() = _mostOftenDocName

    init {
        mostOftenDocName()
    }

    private fun mostOftenDocName(){
        viewModelScope.launch {
            getMostOftenDocNameFromDB()
        }
    }

    private suspend fun getMostOftenDocNameFromDB(){
        val mostOftenReq = database.requestDao.getMostOftenCell()
        if (mostOftenReq != null) {
            _mostOftenDocName.value = database.cellDao.getByCellId(mostOftenReq.cellId)?.docInCell
        }

    }
}