package buu.informatics.s59160605.chickenkookkook3.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160605.chickenkookkook3.database.user.User
import buu.informatics.s59160605.chickenkookkook3.database.user.UserDao
import kotlinx.coroutines.*

class LoginViewModel(
    var database: UserDao,
    application: Application
) : AndroidViewModel(application) {
    private var  viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private val _checkLoginComplete = MutableLiveData<Boolean>()
    val checkLoginComplete: LiveData<Boolean>
        get() = _checkLoginComplete

    init {
        Log.i("LoginViewModel","LoginViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginViewModel", "LoginViewModel destroyed")
        viewModelJob.cancel()
    }

    fun clickLogin(){
        uiScope.launch {
            var find = getUser()
            println(find)
            _checkLoginComplete.value = ( checkNull() &&  chechMathUser(find) )
        }
    }

    private fun chechMathUser(find: User?): Boolean {
        return (find?.password == password.value && find?.username == username.value)
    }

    private suspend fun getUser(): User? {
        return withContext(Dispatchers.IO){
            database.getUsername(username.value)
        }
    }

    private fun checkNull(): Boolean {
        return ( username.value != null && password.value != null )
    }


}