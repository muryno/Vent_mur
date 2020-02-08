package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.handleCar
import java.util.ArrayList

class CarFilteredViewModel(finalScore : FilterEntity?) : ViewModel() {


    private val _score = MutableLiveData<ArrayList<Car>>()
    val carList: LiveData<ArrayList<Car>>
        get() = _score

    init {
        _score.value = handleCar(finalScore)
    }



}