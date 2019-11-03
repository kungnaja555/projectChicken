package buu.informatics.s59160605.chickenkookkook3.addjournal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160605.chickenkookkook3.database.hen.Hen
import buu.informatics.s59160605.chickenkookkook3.database.hen.HenDao
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AddJournalViewModel (
    val database: HenDao,
    application: Application
): AndroidViewModel(application){
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var dieNumber = MutableLiveData<String>()

    private val _gotoAbout = MutableLiveData<Boolean>()
    val gotoAbout: LiveData<Boolean>
        get() = _gotoAbout

    private val _gotoData = MutableLiveData<Boolean>()
    val gotoData: LiveData<Boolean>
        get() = _gotoData

    private val _insertComplete = MutableLiveData<Boolean>()
    val insertComplete: LiveData<Boolean>
        get() = _insertComplete

    init {
        Log.i("AddJournalViewModel", "ViewModel created!!")
    }

    fun clickSave() {
        uiScope.launch {
            if (checkNullInput()){
                val current = LocalDateTime.now(ZoneId.of("Asia/Bangkok"))
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

                var date = current.format(formatter)
                val die = dieNumber.value!!.toIntOrNull()

                var newHen= Hen(date = date, die = die)
                insert(newHen)
                dieNumber.value = ""
                _insertComplete.value = true
            }

        }
    }

    private suspend fun insert(hen: Hen) {
        withContext(Dispatchers.IO){
            database.insert(hen)
        }
    }

    private fun checkNullInput(): Boolean {
        return dieNumber.value != null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("AddJournalViewModel", "ViewModel destroyed!!")

    }

    fun doneShowingSnackbar() {
        _insertComplete.value = false
    }

    fun doneGotoAbout(){
        _gotoAbout.value = true
    }

    fun doneGotoData(){
        _gotoData.value = true
    }
}