package com.ven.murainoyakububiola.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ven.murainoyakububiola.services.model.FilterEntity


class CarModelFactory(private val filter: FilterEntity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarListViewModel::class.java)) {
            return CarListViewModel(filter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}