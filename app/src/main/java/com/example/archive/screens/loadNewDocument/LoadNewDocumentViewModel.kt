package com.example.archive.screens.loadNewDocument

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Cell
import com.example.archive.database.enteties.Document
import com.example.archive.database.enteties.DocumentTheme
import kotlinx.coroutines.launch

class LoadNewDocumentViewModel(
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



    fun updateForm(docName: String, docCopyCount: Int, docTheme:String){
        viewModelScope.launch {
            createDoc(docName, docCopyCount, docTheme)
        }
    }

    private suspend fun createDoc(docName: String, docCopyCount: Int, docTheme:String){
        fillCell(docName, docCopyCount)
        for (i in 0 until docCopyCount){
            database.documentDao.insert(Document(nameDoc = docName, docTheme = docTheme))
        }
        Log.d("NEW DOCS: ", "new docs is made")
    }

    private suspend fun fillCell(docName: String, docCopyCount: Int){
        val cell = database.cellDao.get(docName)
        if (cell == null){
            val emptyCell = database.cellDao.findEmptyCell()
            if (emptyCell == null){
                database.cellDao.insert(Cell(docInCell = docName, docCount = docCopyCount))
                Log.d("NEW CELL: ", "new cell is made")
            }
            else{
                emptyCell.docInCell = docName
                emptyCell.docCount = docCopyCount
                emptyCell.creationDate = System.currentTimeMillis()
                database.cellDao.update(emptyCell)
                Log.d("REMADE CELL: ", "EMPTY CELL WAS REFILLED")
            }
        }
        else{
            cell.docCount += docCopyCount
            database.cellDao.update(cell)
            Log.d("EDIT CELL: ", "CELL WAS UPDATED")
        }
    }
}