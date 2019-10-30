package buu.informatics.s59160605.chickenkookkook3.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160605.chickenkookkook3.database.user.UserDao
import java.lang.IllegalArgumentException

class RegisterViewModelFactory(
    private val dataSource: UserDao,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(dataSource,application) as T
        }else{
            throw IllegalArgumentException("Unknown Register ViewModel class")
        }
    }


}