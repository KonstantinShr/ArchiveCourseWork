package com.example.archive.screens.docCountOnTheme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.DocumentTheme
import kotlinx.coroutines.launch

class DocCountOnThemeViewModel(
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

    private lateinit var themes: List<DocumentTheme>

    private var _themesString = MutableLiveData<List<String>>()
    val themesString: LiveData<List<String>>
        get() = _themesString

    init {
        getAllThemes()
    }

    private var themeStrings: MutableList<String> = mutableListOf()

    private fun getAllThemes(){
        viewModelScope.launch {
            themes = getThemesFromDatabase()
            for (i in themes.indices){
                themeStrings.add(themes[i].theme)
            }
            _themesString.value = themeStrings
        }
    }

    private suspend fun getThemesFromDatabase(): List<DocumentTheme>{
        return database.documentThemeDao.getAllTheme()
    }

    private var _docsCount = MutableLiveData<Int>()
    val docsCount: LiveData<Int>
        get() = _docsCount

    fun updateForm(docTheme:String){
        viewModelScope.launch {
            getDocs(docTheme)
        }
    }

    private suspend fun getDocs(docTheme:String){
        _docsCount.value = database.documentDao.getDocsByTheme(docTheme)?.size
    }
}