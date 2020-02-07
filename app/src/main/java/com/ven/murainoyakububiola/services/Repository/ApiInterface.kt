package com.ven.murainoyakububiola.services.Repository



import com.ven.murainoyakububiola.services.model.FilterEntity
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("assessment/filter.json")
    fun getFilter(): Call<List<FilterEntity>>




}
