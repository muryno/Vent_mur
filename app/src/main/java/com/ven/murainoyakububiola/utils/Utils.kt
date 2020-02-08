package com.ven.murainoyakububiola.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment

import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import de.siegmar.fastcsv.reader.CsvContainer
import de.siegmar.fastcsv.reader.CsvReader
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*


@TargetApi(Build.VERSION_CODES.M)
fun isOnline(): Boolean {

    if ( MainApplication.instance?.applicationContext == null)
        return false

    val cm = MainApplication.instance?.applicationContext
        ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager



    val activeNetwork = cm.activeNetwork
    return activeNetwork != null
}


fun readCsv(): ArrayList<Car>? {
    val data : ArrayList<Car> = ArrayList()

    try {


        val csvfile =
            File(Environment.getExternalStorageDirectory().toString() + "/venten/car_ownsers_data.csv")

        val csvReader = CsvReader()
        csvReader.setContainsHeader(true)

        val csv: CsvContainer = csvReader.read(csvfile, StandardCharsets.UTF_8)
        for (row in csv.rows) {


            val carData = Car()

            carData.id = row.getField(0)
            carData.bio = row.getField(10)
            carData.car_color = row.getField(7)
            carData.car_model = row.getField(5)
            carData.car_model_year = row.getField(6)
            carData.country = row.getField(4)


            carData.email = row.getField(3)
            carData.first_name = row.getField(1)
            carData.last_name = row.getField(2)
            carData.gender = row.getField(8)
            carData.job_title = row.getField(9)

            data.add(carData)



        }
    }catch ( e: IOException){
        e.cause
        return null
    }

    return  data


}







fun handleCarFiltering( data  : FilterEntity?):ArrayList<Car>? {


    val carsDat: ArrayList<Car>? = ArrayList()

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


return carsDat
}