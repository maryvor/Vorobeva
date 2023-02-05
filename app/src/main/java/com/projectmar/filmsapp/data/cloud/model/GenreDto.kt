package com.projectmar.filmsapp.data.cloud.model

import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("genre")
    val genre: String
)
