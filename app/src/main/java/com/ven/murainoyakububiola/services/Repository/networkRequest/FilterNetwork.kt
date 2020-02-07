package com.ven.murainoyakububiola.services.Repository.networkRequest


import com.ven.murainoyakububiola.services.Repository.FilteredRepository.saveFilter
import com.ven.murainoyakububiola.services.Repository.RetrofitClient
import com.ven.murainoyakububiola.services.model.FilterEntity
import com.ven.murainoyakububiola.view.errorMessageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilterNetwork {

    fun fetchFilters( callback: errorMessageView) {
        RetrofitClient.getInstance()?.getApi()?.getFilter()?.enqueue(object : Callback<List<FilterEntity>>{
            override fun onFailure(call: Call<List<FilterEntity>>, t: Throwable) {
                //to pass error message to view model
                callback.loadingFailed("An error occur while fetching data from the server ")
            }

            override fun onResponse(call: Call<List<FilterEntity>>, response: Response<List<FilterEntity>>) {
                if (response.isSuccessful && response.body() != null && response.body() != null) {
                    response.body()?.let { saveFilter(it) }
                    callback.loadingSuccessful("successful")

                } else{
                    callback.loadingFailed(response.message())
                }

            }
        })

    }


}