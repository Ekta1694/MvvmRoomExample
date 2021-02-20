package com.example.mvvm.view.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm.model.People
import com.example.mvvm.view.interfaces.PeopleDao

@Database(entities = arrayOf(People::class),version = 1,exportSchema = false)
@TypeConverters(RoomTypeConverter::class)
 abstract class AppDatabase: RoomDatabase()
{

    companion object
    {
        private val LOG_TAG = AppDatabase::class.java.simpleName
        private val LOCK = Any()
        var DATABASE_NAME = "peopledb"
        private var sInstance: AppDatabase? = null


        open fun getInstance(context: Context): AppDatabase? {
            if (sInstance == null) {
                Log.d(LOG_TAG, "Creating new database instance")
                sInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, AppDatabase.DATABASE_NAME
                )
                    .build()
            }
            Log.d(LOG_TAG, "Getting the database instance")
            return sInstance
        }


    }

    abstract fun peopleDao(): PeopleDao?
}