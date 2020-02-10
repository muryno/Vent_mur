package com.ven.murainoyakububiola.services.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.ven.murainoyakububiola.services.db.dao.FilterDao

import com.ven.murainoyakububiola.services.db.converter.Converter
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.MainApplication

@Database(entities = [FilterEntity::class], version = 1, exportSchema = false)

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