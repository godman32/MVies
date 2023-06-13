package com.gm.mvies.feature.review

import com.google.gson.annotations.SerializedName


data class Reviews (

    @SerializedName("id"            ) var id           : Int?               = null,
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var reviews      : ArrayList<Review> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null

)