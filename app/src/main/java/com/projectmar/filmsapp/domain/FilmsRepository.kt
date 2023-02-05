package com.projectmar.filmsapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface FilmsRepository {

    suspend fun getTopList(): LiveData<PagingData<FilmInfo>>

    suspend fun getDetails(filmId: String): FilmDetails

    suspend fun addToFavourite(film: FilmInfo)

    suspend fun deleteFromFavourite(filmId: String)
}

