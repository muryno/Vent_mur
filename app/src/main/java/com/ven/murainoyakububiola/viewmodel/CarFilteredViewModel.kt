package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.handleCar
import java.util.*

class CarFilteredViewModel(finalScore : FilterEntity?) : ViewModel() {


    //getting array list of car
    private val _car = MutableLiveData<ArrayList<Car>>()
    val carList: LiveData<ArrayList<Car>>
        get() = _car

    init {
        _car.value = handleCar(finalScore)
    }



}