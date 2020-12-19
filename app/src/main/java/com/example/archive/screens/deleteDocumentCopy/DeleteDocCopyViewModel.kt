package com.example.archive.screens.deleteDocumentCopy

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Document
import kotlinx.coroutines.launch

class DeleteDocCopyViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {
    private var _navigateToAdminPanel = MutableLiveData<String?>()
    val navigateToAdminPanel: LiveData<String?>
        get() = _navigateToAdminPanel

    fun onBack(){
        _navigateToAdminPanel.value = username
    }

    fun doneNavigateToAdminPanel(){
        _navigateToAdminPanel.value = null
    }

    fun updateForm(docName: String){
        viewModelScope.launch {
            deleteDocCopy(docName)
        }
    }

    private suspend fun deleteDocCopy(docName: String){
        val document: Document? = database.documentDao.get(docName)
        if (document == null){
            Log.d("DOCUMENT NOT FOUND", "ТАКОГО ДОКУМЕНТА НЕ СУЩЕСТВУЕТ")
        }
        else{
            database.documentDao.delete(document)
            val cellOfThisDoc = database.cellDao.get(docName)
            if (cellOfThisDoc == null){
                Log.d("CELL NOT FOUND", "ТАКОЙ ЯЧЕЙКИ НЕ СУЩЕСТВУЕТ")
            }
            else{
                cellOfThisDoc.docCount -= 1
                database.cellDao.update(cellOfThisDoc)
            }
        }
    }
}