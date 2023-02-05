package com.projectmar.filmsapp.data.cloud.model

import com.google.gson.annotations.SerializedName


data class FilmDetailsDto(
    @SerializedName("kinopoiskId")
    val id: String,

    @SerializedName("nameRu")
    val name: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("genres")
    val genres: List<GenreDto>,

    @SerializedName("countries")
    val countries: List<CountryDto>,

    @SerializedName("year")
    val year: String?,

    @SerializedName("posterUrl")
    val posterUrl: String
)