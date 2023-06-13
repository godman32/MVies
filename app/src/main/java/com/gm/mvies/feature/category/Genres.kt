package com.gm.mvies.feature.category


import com.google.gson.annotations.SerializedName


data class Genres (
    @SerializedName("genres" ) var genres : ArrayList<Genre> = arrayListOf()
)