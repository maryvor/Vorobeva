package com.projectmar.filmsapp.data.cloud.model

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("country")
    val country: String
)
