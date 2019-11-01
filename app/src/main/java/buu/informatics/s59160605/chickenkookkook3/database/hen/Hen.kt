package buu.informatics.s59160605.chickenkookkook3.database.hen

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hen")
data class Hen(
    @PrimaryKey(autoGenerate = true)
    var henId: Int = 0,

    @ColumnInfo(name = "date")
    var date: String?,

    @ColumnInfo(name = "die")
    var die: String?
)

