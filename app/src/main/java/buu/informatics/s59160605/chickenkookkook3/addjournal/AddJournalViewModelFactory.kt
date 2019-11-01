package buu.informatics.s59160605.chickenkookkook3.addjournal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160605.chickenkookkook3.database.hen.HenDao
import java.lang.IllegalArgumentException

class AddJournalViewModelFactory (
    private val dataSource: HenDao,
    private val application: Application,
    private val username: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddJournalViewModel::class.java)){
            return AddJournalViewModel(dataSource,application,username) as T
        }else{
            throw IllegalArgumentException("Unknown Login ViewModel Class")
        }
    }

}