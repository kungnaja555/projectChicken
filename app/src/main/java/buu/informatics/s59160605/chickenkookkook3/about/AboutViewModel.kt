package buu.informatics.s59160605.chickenkookkook3.about

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160605.chickenkookkook3.database.user.User
import buu.informatics.s59160605.chickenkookkook3.database.user.UserDao
import kotlinx.coroutines.*

class AboutViewModel (
    val database: UserDao,
    application: Application,
    val username: String
): AndroidViewModel(application){
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val user = MutableLiveData<String>()
    val pass = MutableLiveData<String>()

    private val _gotoAddJournal = MutableLiveData<Boolean>()
    val gotoAddJournal: LiveData<Boolean>
        get() = _gotoAddJournal

    init {
        Log.i("AboutViewModel","About View Model created!!")
        getUser()
    }

    private fun getUser() {
        uiScope.launch {
            val find = getCurrentUser()
            user.value = find?.username
            pass.value = find?.password
        }
    }

    private suspend fun getCurrentUser(): User? {
        return withContext(Dispatchers.IO){
            database.getUsername(username)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun clickback(){
        _gotoAddJournal.value = true
    }

}