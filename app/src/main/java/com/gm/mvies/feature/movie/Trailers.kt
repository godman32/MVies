package com.gm.mvies.feature.movie

import com.google.gson.annotations.SerializedName


data class Trailers (

    @SerializedName("id"      ) var id      : Int?               = null,
    @SerializedName("results" ) var trailers : ArrayList<Trailer> = arrayListOf()

)