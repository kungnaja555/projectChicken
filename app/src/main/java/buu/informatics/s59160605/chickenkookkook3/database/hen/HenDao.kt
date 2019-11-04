package buu.informatics.s59160605.chickenkookkook3.database.hen

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import buu.informatics.s59160605.chickenkookkook3.database.hen.Hen

@Dao
interface HenDao{
    @Insert
    fun insert(hen: Hen)

    @Query("SELECT * FROM hen ORDER BY henId DESC LIMIT 15")
    fun getAll(): LiveData<List<Hen>>

    @Query("SELECT SUM(die) FROM hen")
    fun getDie(): Int?
}