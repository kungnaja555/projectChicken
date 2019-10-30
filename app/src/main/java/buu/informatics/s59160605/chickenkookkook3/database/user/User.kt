package buu.informatics.s59160605.chickenkookkook3.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "username")
    var username: String?,

    @ColumnInfo(name = "password")
    var password: String?
)