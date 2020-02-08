package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import java.util.ArrayList


class CarFilteredModelFactory(private val data: FilterEntity?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarFilteredViewModel::class.java)) {
            return CarFilteredViewModel(data) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}