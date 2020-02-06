package com.ven.murainoyakubu.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.ven.murainoyakubu.db.dao.FilterDao
import com.ven.murainoyakubu.MainApplication
import com.ven.murainoyakubu.db.converter.Converter
import com.ven.murainoyakubu.db.model.Filter

@Database(entities = [Filter::class], version = 3, exportSchema = false)

@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun FilterDao(): FilterDao



    companion object{
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = MainApplication.instance?.applicationContext?.let {
                        Room.databaseBuilder(it, AppDatabase::class.java, "ven").fallbackToDestructiveMigration()
                            .allowMainThreadQueries().build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}