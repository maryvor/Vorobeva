package com.projectmar.filmsapp.domain

data class FilmInfo(
    val id: String,
    val name: String,
    val genres: String,
    val countries: String,
    val year: String,
    val isFavourite: Boolean,
    val smallImg: String
)
