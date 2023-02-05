package com.projectmar.filmsapp.data.cloud.model

import com.google.gson.annotations.SerializedName

data class FilmsDtoContainer(
    @SerializedName("films")
    val films: List<FilmModelDto>
)