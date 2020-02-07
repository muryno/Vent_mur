package com.ven.murainoyakububiola.services.Repository.networkRequest


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ven.murainoyakububiola.services.Repository.RetrofitClient
import com.ven.murainoyakububiola.services.model.Filter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilterPresenter() {

    fun fetchFilters(): LiveData<String> {
        val errorMessage = MutableLiveData<String>()
        RetrofitClient.getInstance()?.getApi()?.getFilter()?.enqueue(object : Callback<List<Filter>>{
            override fun onFailure(call: Call<List<Filter>>, t: Throwable) {
                callback.loadingFailed("An error occur while fetching data from the server ")
            }

            override fun onResponse(call: Call<List<Filter>>, response: Response<List<Filter>>) {
                if (response.isSuccessful && response.body() != null && response.body() != null) {
                    response.body()?.let { saveFilter(it) }
                    callback.loadingSuccessful("")
                } else callback.loadingFailed(response.message())

            }
        })
    }


}