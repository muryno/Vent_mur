package com.ven.murainoyakububiola.services

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.ven.murainoyakububiola.db.dao.FilterDao

import com.ven.murainoyakububiola.db.converter.Converter
import com.ven.murainoyakubu.db.model.Filter
import com.ven.murainoyakububiola.MainApplication

@Database(entities = [Filter::class], version = 1, exportSchema = false)

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