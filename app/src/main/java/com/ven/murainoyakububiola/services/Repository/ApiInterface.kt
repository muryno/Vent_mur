package com.ven.murainoyakubu.server



import com.ven.murainoyakubu.db.model.Filter
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("assessment/filter.json")
    fun getFilter(): Call<List<Filter>>




}
