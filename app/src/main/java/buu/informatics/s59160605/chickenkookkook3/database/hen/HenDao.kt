package buu.informatics.s59160605.chickenkookkook3.database.hen

import androidx.room.Dao
import androidx.room.Insert
import buu.informatics.s59160605.chickenkookkook3.database.hen.Hen

@Dao
interface HenDao{
    @Insert
    fun insert(hen: Hen)
}