package com.ven.murainoyakububiola.services.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "filters")
class FilterEntity : Serializable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("start_year")
    @Expose
    var startYear: Int? = null

    @SerializedName("end_year")
    @Expose
    var endYear: Int? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("countries")
    @Expose
    var countries: List<String>? = null

    @SerializedName("colors")
    @Expose
    var colors: List<String>? = null


    constructor()
    @Ignore
    constructor(startYear: Int, endYear: Int,gender: String,countries: List<String>,colors: List<String>?){
        this.startYear = startYear
        this.endYear = endYear
        this.gender = gender
        this.countries = countries
        this.colors  = colors
    }
}