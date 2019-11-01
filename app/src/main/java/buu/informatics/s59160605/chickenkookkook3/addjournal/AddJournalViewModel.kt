package buu.informatics.s59160605.chickenkookkook3.addjournal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import buu.informatics.s59160605.chickenkookkook3.database.hen.HenDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AddJournalViewModel (
    val database: HenDao,
    application: Application,
    username: String
): AndroidViewModel(application){
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        Log.i("AddJournalViewModel", "ViewModel created!!")
        Log.i("AddJournalViewModel", username)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.i("AddJournalViewModel", "ViewModel destroyed!!")

    }
}