package buu.informatics.s59160605.chickenkookkook3.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import buu.informatics.s59160605.chickenkookkook3.database.user.User


@Dao
interface UserDao{

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUsername(username: String?): User?

    @Query("DELETE FROM user WHERE userId = :key")
    fun delete(key: Int)

}