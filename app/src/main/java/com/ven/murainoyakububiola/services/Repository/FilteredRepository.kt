package com.ven.murainoyakububiola.services.Repository

import androidx.lifecycle.LiveData
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.services.db.AppDatabase.Companion.getAppDataBase
import com.ven.murainoyakububiola.services.model.FilterEntity

object FilteredRepository {




    fun saveFilter(data:List<FilterEntity>) {
        MainApplication.executorService.execute {
            getAppDataBase()?.FilterDao()?.nukeFilterTable()
            getAppDataBase()?.FilterDao()?.saveFilter(data)
        }
    }

    val getFilterEntity:  LiveData<List<FilterEntity>>?
        get() = getAppDataBase()?.FilterDao()?.getFilter()







}