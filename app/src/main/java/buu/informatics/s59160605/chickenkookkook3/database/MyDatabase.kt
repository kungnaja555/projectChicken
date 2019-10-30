package buu.informatics.s59160605.chickenkookkook3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import buu.informatics.s59160605.chickenkookkook3.database.user.User
import buu.informatics.s59160605.chickenkookkook3.database.user.UserDao

@Database(entities = [User::class],version = 1,exportSchema = false)
abstract class MyDatabase : RoomDatabase(){

    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "myDB"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}