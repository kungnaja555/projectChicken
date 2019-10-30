package buu.informatics.s59160605.chickenkookkook3.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import buu.informatics.s59160605.chickenkookkook3.database.user.User
import buu.informatics.s59160605.chickenkookkook3.database.user.UserDao
import kotlinx.coroutines.*

class RegisterViewModel(
    val database: UserDao,
    application: Application
): AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var confirmPassword = MutableLiveData<String>()
    var alertText = MutableLiveData<String>()

    private val _registerComplete = MutableLiveData<Boolean>()
    val registerComplete: LiveData<Boolean>
        get() = _registerComplete

    init {
        alertText.value = ""
        Log.i("RegisterViewModel","RegisterViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("RegisterViewModel","RegisterViewModel destroyed")
        viewModelJob.cancel()
    }

    fun clickRegister(){
        uiScope.launch {
            var find = getUsername()
            if ( checkNull() ) alertText.value = "กรุณากรอกข้อมูลให้ครบถ้วน"
            else if ( checkMatchUsername(find) ) alertText.value = "ชื่อผู้ใช้นี้ถูกใช้งาน"
            else if ( !checkMatchPassword() ) alertText.value = "รหัสผ่านและยืนยันรหัสผ่านไม่ตรกัน"
            else {
                var newUser = User(username = username.value , password =  password.value)
                insert(newUser)
                _registerComplete.value = true
            }
        }
    }

    // Method check
    private fun checkNull(): Boolean {
        return (username.value == null || password.value == null || confirmPassword.value == null)
    }

    private fun checkMatchPassword(): Boolean {
        return (password.value == confirmPassword.value)
    }

    private fun checkMatchUsername(find: User?): Boolean {
        return find==null
    }


    // Method contact with database
    private suspend fun insert(newUser: User) {
        withContext(Dispatchers.IO){
            database.insert(newUser)
        }
    }

    private suspend fun getUsername(): User? {
        return withContext(Dispatchers.IO){
            database.getUsername(username.value)
        }
    }


}