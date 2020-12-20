package com.example.archive.screens.lastUsernameInDoc

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Request
import kotlinx.coroutines.launch

class LastUsernameInDocViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

    private var _navigateToCheckingRequests = MutableLiveData<String?>()
    val navigateToCheckingRequests: LiveData<String?>
        get() = _navigateToCheckingRequests

    fun onBack(){
        _navigateToCheckingRequests.value = username
    }

    fun doneNavigateToCheckingRequests(){
        _navigateToCheckingRequests.value = null
    }

    private var _whoWasLast = MutableLiveData<String>()
    val whoWasLast: LiveData<String>
        get() = _whoWasLast

    fun updateForm(dn: String){
        viewModelScope.launch {
            lastUsername(dn)
        }
    }

    private suspend fun lastUsername(docName: String){
        val cell = database.cellDao.get(docName)
        if (cell == null){
            Log.d("DOC NOT FOUND", "ТАКОГО ДОКУМЕНТА НЕ СУЩЕСТВУЕТ")
        }
        val lastReq = cell?.let { database.requestDao.lastReqToCell(cell.cellId) }
        if (lastReq == null){
            Log.d("REQUESTS NOT FOUND", "К ДАННОМУ ДОКУМЕНТУ ЕЩЕ НЕ ОБРАЩАЛИСЬ")
        }
        _whoWasLast.value = lastReq?.username
    }
}