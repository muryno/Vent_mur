package com.ven.murainoyakububiola.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.R
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.handleCar
import com.ven.murainoyakububiola.utils.readCsv
import com.ven.murainoyakububiola.view.adapter.CarFilteredAdapter
import com.ven.murainoyakububiola.view.base.BaseActivity
import com.ven.murainoyakububiola.viewmodel.FilteredListViewModel
import kotlinx.android.synthetic.main.activity_car_filtered_list.*
import java.util.ArrayList

class CarFilteredListActivity : BaseActivity() {




    private var viewModel: FilteredListViewModel? = null

    private  var adapter: CarFilteredAdapter?= null
    var data :FilterEntity ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_filtered_list)


        if (intent != null) {
            data = intent.getSerializableExtra("data") as FilterEntity?
            if (data == null) {
                finish()

                //if the data is null, it should close the page, nothing to display
            }
        }
            adapter = CarFilteredAdapter()
            val manager = LinearLayoutManager(this)
            _recyclerView__car?.layoutManager = manager
            _recyclerView__car?.adapter = adapter



            setToolbar(toolbar, "Car owners ")

             getDatas(data)


        }


    private fun getDatas(ty : FilterEntity?){
        val carsDat  = handleCar(ty)

        if (carsDat != null) {
            adapter?.addItems(carsDat)
        }
    }

}
