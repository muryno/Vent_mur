package com.ven.murainoyakububiola.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.services.Repository.FilteredRepository
import com.ven.murainoyakububiola.services.Repository.networkRequest.FilterNetwork
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.readCsv
import com.ven.murainoyakububiola.view.errorMessageView

class CarListViewModel(private val filterData: FilterEntity) : ViewModel() {
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */




    private val _carData = MutableLiveData<ArrayList<Car>>()
    val carData: LiveData<ArrayList<Car>>
        get() = _carData


    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage







    }
