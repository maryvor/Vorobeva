package com.projectmar.filmsapp.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.projectmar.filmsapp.data.cloud.model.Result

interface FilmsRepository {

    suspend fun getTopList(): LiveData<PagingData<FilmInfo>>

    suspend fun getDetails(filmId: String): Result<FilmDetails>

}

