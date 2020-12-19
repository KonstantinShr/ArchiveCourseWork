package com.example.archive.screens.themeOfDoc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import kotlinx.coroutines.launch

class ThemeOfDocViewModel(
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

    private var _themeOfDoc = MutableLiveData<String>()
    val themeOfDoc: LiveData<String>
        get() = _themeOfDoc

    fun updateForm(docName: String){
        viewModelScope.launch {
            determineTheme(docName)
        }
    }

    private suspend fun determineTheme(docName: String){
        val doc = database.documentDao.get(docName)
        if (doc != null){
            _themeOfDoc.value = doc.docTheme
        }
    }
}