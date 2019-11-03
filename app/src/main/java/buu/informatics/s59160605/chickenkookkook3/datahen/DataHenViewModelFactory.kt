package buu.informatics.s59160605.chickenkookkook3.datahen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.informatics.s59160605.chickenkookkook3.database.hen.HenDao
import java.lang.IllegalArgumentException

class DataHenViewModelFactory (
    private val dataSource : HenDao,
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DataHenViewModel::class.java)){
            return DataHenViewModel(dataSource,application) as T
        }else{
            throw IllegalArgumentException("Unknowm Data Hen Veiw Model Class")
        }
    }

}