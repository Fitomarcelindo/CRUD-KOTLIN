package by.marcel.crud.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import by.marcel.crud.data.dao.UserDao
import by.marcel.crud.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun UserDao(): UserDao

    companion object{
        private var  instance: AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            if (instance==null){
                instance = databaseBuilder(context, AppDatabase::class.java, "Marcel-Data")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }

        
    }
}