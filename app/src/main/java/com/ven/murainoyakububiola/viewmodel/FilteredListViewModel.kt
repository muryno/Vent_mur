package com.ven.murainoyakububiola.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ven.murainoyakububiola.MainApplication
import com.ven.murainoyakububiola.services.Repository.FilteredRepository
import com.ven.murainoyakububiola.services.Repository.networkRequest.FilterNetwork
import com.ven.murainoyakububiola.services.model.Car
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.utils.handleCar
import com.ven.murainoyakububiola.utils.readCsv
import com.ven.murainoyakububiola.view.errorMessageView
import java.util.ArrayList

class FilteredListViewModel : ViewModel(), errorMessageView {
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    val getFilteredList  :LiveData<List<FilterEntity>>?=  FilteredRepository.getFilterEntity





    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String>
        get() = _successMessage


    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    private val _filteredData = MutableLiveData<ArrayList<Car>>()

    val filteredData  :LiveData<ArrayList<Car>>? = _filteredData

   //make Network request
    fun loadFromServer(){
         FilterNetwork().fetchFilters(this)
    }

    //listning to erro message from FilterNetwork Activity
    override fun loadingFailed(msg: String) {
        _errorMessage.value  = msg


    }

    //listning to success message from FilterNetwork Activity
    override fun loadingSuccessful(msg: String) {
        _successMessage.value = msg
    }


    /**
     * The filtered data it pass to ArrayList<Car> and it will be observe by CarFilteredActivity
     */
    fun handleCarFiltering( data  : FilterEntity?) {
        val carsDat: ArrayList<Car>? = handleCar(data)
        _filteredData.value = carsDat
    }
}