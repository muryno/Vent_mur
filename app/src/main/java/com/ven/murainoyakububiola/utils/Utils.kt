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

/** check if device is online**/
@TargetApi(Build.VERSION_CODES.M)
fun isOnline(): Boolean {

    if ( MainApplication.instance?.applicationContext == null)
        return false

    val cm = MainApplication.instance?.applicationContext
        ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager



    val activeNetwork = cm.activeNetwork
    return activeNetwork != null
}

/** to read from csv file**/
fun readCsv(csvfile: File?): ArrayList<Car>? {
    val data : ArrayList<Car> = ArrayList()

    try {





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






/** logic onn how to filter car csv file**/

fun handleCar( data  : FilterEntity?):ArrayList<Car>? {


    /** to read from external storage**/
    val carsDat: ArrayList<Car>? = ArrayList()
        val carsData: ArrayList<Car>? = readCsv(readFromExternalStorage())


        //
        /**filter by start and end date alone**/

        if (data?.colors?.size ?: 0 < 1 && data?.gender.isNullOrEmpty() && data?.countries?.size ?: 0 < 1) {
            carsData?.filter {

                (data?.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                        it.car_model_year?.toInt() ?: 0 >= data?.endYear ?: 0)
            }?.let { carsDat?.addAll(it) }

        }


        /**filter by date and countries**/
        else if (data?.colors?.size ?: 0 < 1 && data?.gender.isNullOrEmpty() && data?.countries?.size ?: 0 > 1) {
            carsData?.filter {


                (data?.countries?.contains(it.country) == true) &&

                        (data.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                                it.car_model_year?.toInt() ?: 0 >= data.endYear ?: 0)

            }?.let { carsDat?.addAll(it) }
        }


        /**filter by date and colors**/
        else if (data?.colors?.size ?: 0 > 1 && data?.gender.isNullOrEmpty() && data?.countries?.size ?: 0 < 1) {
            carsData?.filter {


                (data?.colors?.contains(it.car_color) == true) &&

                        (data.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                                it.car_model_year?.toInt() ?: 0 >= data.endYear ?: 0)

            }?.let { carsDat?.addAll(it) }
        }


        /**filter by date and gender**/

        else if (data?.colors?.size ?: 0 < 1 && data?.gender?.isNotEmpty() == true && data?.countries?.size ?: 0 < 1) {
            carsData?.filter {

                (it.gender == data?.gender) &&
                        (data?.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                                it.car_model_year?.toInt() ?: 0 >= data.endYear ?: 0)


            }?.let { carsDat?.addAll(it) }

        }


        /**filter all**/
        else if (data?.colors?.size ?: 0 > 1 && data?.gender?.isNotEmpty() == true && data.countries?.size ?: 0 > 1) {
            carsData?.filter {

                (it.gender == data.gender) &&

                        (data.countries?.contains(it.country) == true) &&

                        (data.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                                it.car_model_year?.toInt() ?: 0 >= data.endYear ?: 0) &&

                        data.colors?.contains(it.car_color) == true
            }?.let { carsDat?.addAll(it) }

        }


            /**filter by date color and countries without gender**/
            else if (data?.colors?.size ?: 0 > 1 && data?.gender.isNullOrEmpty() && data?.countries?.size ?: 0 > 1) {
            carsData?.filter {


                (data?.countries?.contains(it.country) == true) &&

                        (data.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                                it.car_model_year?.toInt() ?: 0 >= data?.endYear ?: 0) &&

                        data.colors?.contains(it.car_color) == true
            }?.let { carsDat?.addAll(it) }



            /**filter by date color and gender without countries**/
        } else if (data?.colors?.size ?: 0 > 1 && data?.gender?.isNotEmpty()==true && data.countries?.size ?: 0 < 1) {
            carsData?.filter {

                (it.gender == data.gender) &&


                        (data.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                                it.car_model_year?.toInt() ?: 0 >= data.endYear ?: 0) &&

                        data.colors?.contains(it.car_color) == true
            }?.let { carsDat?.addAll(it) }


        }


        /**filter by date countries and gender without color**/
        else if (data?.colors?.size ?: 0 < 1 && data?.gender?.isNotEmpty()==true && data.countries?.size ?: 0 > 1) {
            carsData?.filter {

                (it.gender == data.gender) &&

                        (data.countries?.contains(it.country) == true) &&

                        (data.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 &&
                                it.car_model_year?.toInt() ?: 0 >= data.endYear ?: 0)

            }?.let { carsDat?.addAll(it) }

            /**fetch without filter all**/
        }
        else {
            carsData?.filter {

                (it.gender == data?.gender) ||

                        (data?.countries?.contains(it.country) == true) ||

                        (data?.startYear ?: 0 <= it.car_model_year?.toInt() ?: 0 ||
                                it.car_model_year?.toInt() ?: 0 >= data?.endYear ?: 0) ||

                        data?.colors?.contains(it.car_color) == true
            }?.let { carsDat?.addAll(it) }

        }

    return carsDat
}



/**Reading file from external storage is depreciated from API 29 and testing will fail **/
  fun readFromExternalStorage(): File? {

      val csvfile: File?
    try {
         csvfile = File(Environment.getExternalStorageDirectory().toString() + "/venten/car_ownsers_data.csv")

    }catch ( e: IOException){
        e.cause
        return null
    }
    return csvfile
}


fun readFromExternalFilesDir(): File? {

    val csvfile: File?
    try {
        csvfile = File(MainApplication.instance?.applicationContext?.getExternalFilesDir("/venten/car_ownsers_data.csv").toString())

    }catch ( e: IOException){
        e.cause
        return null
    }
    return csvfile
}