package com.ven.murainoyakububiola.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.R
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.readCsv
import com.ven.murainoyakububiola.view.adapter.CarFilteredAdapter
import com.ven.murainoyakububiola.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_car_filtered_list.*

class CarFilteredListActivity : BaseActivity() {

   private var data  : FilterEntity ?= null




    private  var adapter: CarFilteredAdapter?= null
    val carsDat: ArrayList<Car>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_filtered_list)

        adapter = CarFilteredAdapter()
        val manager = LinearLayoutManager(this)
        _recyclerView__car?.layoutManager = manager
        _recyclerView__car?.adapter = adapter


        if (intent != null) {
            data = intent.getSerializableExtra("data") as FilterEntity

            if (data != null) {
                handleCarFiltering(data)
            }
        }
        setToolbar(toolbar, "Car owners ")


    }


    fun handleCarFiltering( data  : FilterEntity?){



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
            }?.let { carsDat?.addAll(it) }
        }


        carsDat?.let { adapter?.addItems(it) }
    }
}
//v