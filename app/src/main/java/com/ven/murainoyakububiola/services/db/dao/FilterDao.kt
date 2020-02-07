package com.ven.murainoyakububiola.services.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.ven.murainoyakububiola.services.model.FilterEntity


@Dao
interface FilterDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFilter(filterEntity: FilterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFilter(filterEntity: List<FilterEntity>)

    @Query("SELECT * FROM filters ORDER BY id")
    fun getFilter(): LiveData<List<FilterEntity>>





}