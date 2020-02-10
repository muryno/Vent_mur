package com.ven.murainoyakububiola


import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.handleCar
import com.ven.murainoyakububiola.utils.isOnline
import com.ven.murainoyakububiola.utils.readCsv
import com.ven.murainoyakububiola.utils.readFromExternalFilesDir
import org.junit.Assert
import org.junit.Test
import java.io.File
import java.util.*

class FilteredActivityUnitTest {



    @Test
    fun checkDeviceOnline() {
        Assert.assertEquals(false, isOnline())
    }





    @Test
    @Throws(Exception::class)
    fun csvFileObjectShouldNotBeNull() {

        val readFileDir: File? = readFromExternalFilesDir()

        val file: ArrayList<Car>? = readCsv(readFileDir)
        Assert.assertNull(file)
    }


    @Test
    fun checkCaseWhenAllArePresent(){

        val color = listOf("Green", "Violet", "Yellow", "Blue", "Teal", "Maroon", "Red", "Aquamarine", "Orange", "Mauv")
        val countries = listOf("China", "South Africa", "france", "Mexico", "Japan", "Estonia", "Colombia", "China")
        val dummyData =FilterEntity (1990,2010,"male",countries,color)

        val emty = listOf<String>()

        Assert.assertEquals(emty,handleCar(dummyData))
    }


    @Test
    fun checkCaseWhenGenderNotPresent(){
        val color = listOf("Green", "Violet", "Yellow", "Blue", "Teal", "Maroon", "Red", "Aquamarine", "Orange", "Mauv")
        val countries = listOf("China", "South Africa", "france", "Mexico", "Japan", "Estonia", "Colombia", "China")
        val dummyData =FilterEntity (1990,2010,"",countries,color)



        Assert.assertNotNull(handleCar(dummyData))
    }


    @Test
    fun checkCaseWhenCoutriesisEmty(){
        val color = listOf("Green", "Violet", "Yellow", "Blue", "Teal", "Maroon", "Red", "Aquamarine", "Orange", "Mauv")
        val countries = listOf<String>()
        val dummyData =FilterEntity (1980,2002,"female",countries,color)

        val emty = listOf<String>()

        Assert.assertEquals(emty,handleCar(dummyData))
    }


    @Test
    fun checkCaseWhenGenderColorAndColorAreEmpty(){
        val color = listOf<String>()
        val countries = listOf<String>()
        val dummyData =FilterEntity (1990,2000,"",countries,color)



        Assert.assertNotNull(handleCar(dummyData))
    }


    @Test
    fun checkCaseColorAndGenderNotPresent(){
        val color = listOf<String>()
        val countries = listOf("China", "South Africa", "france", "Mexico", "Japan", "Estonia", "Colombia", "China")
        val dummyData =FilterEntity (1990,2010,"",countries,color)



        Assert.assertNotNull(handleCar(dummyData))
    }


}