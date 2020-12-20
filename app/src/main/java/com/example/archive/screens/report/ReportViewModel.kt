package com.example.archive.screens.report

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.archive.database.ArchiveDatabase
import com.example.archive.database.enteties.Cell
import kotlinx.coroutines.launch

class ReportViewModel(
        val username: String?,
        val database: ArchiveDatabase,
        application: Application): AndroidViewModel(application) {

    private var _report = MutableLiveData<String>()
    val report : LiveData<String>
        get() = _report

    init {
        getReport()
    }

    private fun getReport(){
        viewModelScope.launch {
            getReportInfoFromDB()
        }
    }

    private suspend fun getReportInfoFromDB(){
        val docCount: Int = database.cellDao.getAllCell()?.size ?: 0

        val lastMonthDocs : List<Cell> = database.cellDao.lastMonthDocs(System.currentTimeMillis()) ?: listOf()

        var finalString = "Всего в архиве присутствует $docCount единиц хранения.\n\nЗа последний месяц в архив поступило ${lastMonthDocs.size}\n\nДокументы, поступившие за последний месяц:\n\n"

        for (lastMonthDoc in lastMonthDocs){
            if (lastMonthDoc.docCount != 0) {
                finalString += "Название документа: ${lastMonthDoc.docInCell}\nКоличество экземпляров данного документа: ${lastMonthDoc.docCount}\nДокумент расположен в стеллаже №${(lastMonthDoc.cellId) / 40 + 1}, на полке №${lastMonthDoc.cellId/5+1}, в ячейке №${lastMonthDoc.cellId - 5*(lastMonthDoc.cellId/5)}\n\n"
            }
        }

        _report.value = finalString
    }

    private var _navigateToMain = MutableLiveData<String?>()
    val navigateToMain: LiveData<String?>
        get() = _navigateToMain

    fun onBack(){
        _navigateToMain.value = username
    }

    fun doneNavigateToMain(){
        _navigateToMain.value = null
    }
}