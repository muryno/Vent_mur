package com.ven.murainoyakububiola


import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.utils.isOnline
import com.ven.murainoyakububiola.utils.readCsv
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.ArrayList

class UtilsUnitTest {



    @Test
    fun checkDeviceOnline() {
        Assert.assertEquals(false, isOnline())
    }





    @Test
    @Throws(Exception::class)
    fun csvFileObjectShouldNotBeNull() {


        val file: ArrayList<Car>? = readCsv()
        assertThat(file, notNullValue())
    }

}