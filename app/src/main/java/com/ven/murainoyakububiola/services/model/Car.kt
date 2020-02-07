package com.ven.murainoyakububiola.services.model

import java.io.Serializable


class Car :Serializable {

    var id :String? = null


    var first_name: String? = null


    var last_name: String? = null



    var email: String? = null

    var country: String? = null


    var car_model: String? = null

    var car_model_year: String? = null



    var car_color: String? = null

    var gender: String? = null


    var job_title: String? = null

    var bio: String? = null


    fun fullname():String{
        return "$first_name $last_name"
    }
}