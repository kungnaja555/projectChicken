package buu.informatics.s59160605.chickenkookkook3.about

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160605.chickenkookkook3.database.user.UserDao

class AboutViewModelFactory (
    private val dataSource : UserDao,
    private val application: Application,
    private val username: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AboutViewModel::class.java)){
            return AboutViewModel(dataSource,application,username) as T
        }else{
            throw IllegalAccessException("Unknowm About View Model Class")
        }
    }

}