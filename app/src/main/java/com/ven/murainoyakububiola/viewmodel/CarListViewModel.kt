package com.ven.murainoyakububiola.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ven.murainoyakububiola.services.Repository.FilteredRepository
import com.ven.murainoyakububiola.services.Repository.networkRequest.FilterNetwork
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.view.errorMessageView

class CarListViewModel(private val finalScore: FilterEntity) : ViewModel(), errorMessageView {
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

}