package com.ven.murainoyakububiola.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.guesstheword.screens.score.CarFilteredModelFactory
import com.example.android.guesstheword.screens.score.CarFilteredViewModel
import com.ven.murainoyakububiola.R
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.view.adapter.CarFilteredAdapter
import com.ven.murainoyakububiola.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_car_filtered_list.*

class CarFilteredListActivity : BaseActivity() {





    private lateinit var viewModel: CarFilteredViewModel
    private lateinit var viewModelFactory: CarFilteredModelFactory

    private  var adapter: CarFilteredAdapter?= null
    var data :FilterEntity ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_filtered_list)


        setToolbar(toolbar, "Car owners ")

        //to check read permission
        checkFileWritePermission()



        /**instantiate vadater class and setting it up **/
        adapter = CarFilteredAdapter()
        val manager = LinearLayoutManager(this)
        _recyclerView__car?.layoutManager = manager
        _recyclerView__car?.adapter = adapter



        if (intent != null) { data = intent.getSerializableExtra("data") as FilterEntity? }
        /**instantiate view model and view model factory to pass FilterEntity  and detach the heavy work from activity**/
        viewModelFactory = CarFilteredModelFactory(data)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CarFilteredViewModel::class.java)


        viewModel.carList.observe(this, Observer {
            if (it != null) {
                adapter?.addItems(it)
            }
        })

    }


    //checking for permission

    private fun checkFileWritePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                100
            )
        }
    }



}
