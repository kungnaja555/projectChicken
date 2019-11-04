package buu.informatics.s59160605.chickenkookkook3.datahen

import android.app.Application
import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import buu.informatics.s59160605.chickenkookkook3.database.hen.Hen
import buu.informatics.s59160605.chickenkookkook3.database.hen.HenDao
import kotlinx.coroutines.*

class DataHenViewModel (
    val database: HenDao,
    application: Application
): AndroidViewModel(application){
    val viewModelJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var dieQuantity = MutableLiveData<Int?>()

    val showDieQuantity = Transformations.map(dieQuantity){ die ->
        "จำนวนไก่ตายทั้งหมด : "+die+" ตัว"
    }

    val dieHenAll = database.getAll()

    init {
        Log.i("DataHenViewModel", "ViewModel Craeted!!")
        initialToDie()

    }

    private fun initialToDie() {
        uiScope.launch {
            dieQuantity.value = getDieQuantity()
            Log.i("DataHenViewModel", dieQuantity.value.toString())
        }
    }

    private suspend fun getDieQuantity(): Int?{
        return withContext(Dispatchers.IO){
            var sum = database.getDie()
            if(sum==null){
                sum=0
            }
            sum
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}