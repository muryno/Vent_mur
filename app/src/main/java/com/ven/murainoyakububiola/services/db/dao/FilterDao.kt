package com.ven.murainoyakububiola.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.ven.murainoyakubu.db.model.Filter


@Dao
interface FilterDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFilter(filter: Filter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFilter(filter: List<Filter>)

    @Query("SELECT * FROM filters ORDER BY id")
    fun getFilter(): LiveData<List<Filter>>





}