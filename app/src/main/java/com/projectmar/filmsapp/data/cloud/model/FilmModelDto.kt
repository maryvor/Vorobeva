package com.projectmar.filmsapp.data.cloud.model

import com.google.gson.annotations.SerializedName

data class FilmModelDto(
    @SerializedName("filmId")
    val id: String,

    @SerializedName("nameRu")
    val name: String?,

    @SerializedName("genres")
    val genres: List<GenreDto>,

    @SerializedName("countries")
    val countries: List<CountryDto>,

    @SerializedName("year")
    val year: String?,

    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String
)
