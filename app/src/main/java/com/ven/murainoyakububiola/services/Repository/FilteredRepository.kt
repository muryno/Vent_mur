package com.ven.murainoyakububiola.services.Repository

import androidx.lifecycle.LiveData
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.services.db.AppDatabase.Companion.getAppDataBase
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
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




    fun fil( data  : FilterEntity?): ArrayList<Car>?{

        val cars: ArrayList<Car>? = ArrayList()

        MainApplication.executorService.execute {
            val carsData: ArrayList<Car>? = readCsv()


            carsData?.filter {

                (
                        //check if gender are equal to car data
                        it.gender == data?.gender) ||

                        //check if FilterEntity countries array contain car country
                        (data?.countries?.contains(it.country) == true) ||

                        //date range from start to end
                        (data?.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 ||
                                it.car_model_year?.toInt() ?: 0 >= data?.endYear ?: 0) ||

                        //check if FilterEntity color array contain car color
                        data?.colors?.contains(it.car_color) == true
            }?.let { cars?.addAll(it) }
        }

        return cars
    }
}