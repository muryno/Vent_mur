package com.ven.murainoyakububiola.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ven.murainoyakububiola.R
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.view.base.BaseActivity
import java.io.Serializable

class CarFilteredListActivity : BaseActivity() {

   private var data  : FilterEntity ?= null


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_filtered_list)


        if (intent != null) {
            data = intent.getSerializableExtra("data") as FilterEntity


            Toast.makeText(this,title, Toast.LENGTH_SHORT).show()
            if (data == null) {

            }
        }
    }


    fun handleCarFiltering( data  : FilterEntity){


    }
}
