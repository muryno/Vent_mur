package com.ven.murainoyakububiola.services.Repository

import androidx.lifecycle.LiveData
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.services.db.AppDatabase.Companion.getAppDataBase
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.handleCar
import com.ven.murainoyakububiola.utils.readCsv
import java.util.ArrayList

object FilteredRepository {




    fun saveFilter(data:List<FilterEntity>) {
        MainApplication.executorService.execute {
            getAppDataBase()?.FilterDao()?.saveFilter(data)
        }
    }

    val getFilterEntity:  LiveData<List<FilterEntity>>?
        get() = getAppDataBase()?.FilterDao()?.getFilter()







}