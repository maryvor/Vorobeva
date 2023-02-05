package com.projectmar.filmsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingData
import androidx.paging.map
import com.projectmar.filmsapp.data.cloud.FilmsCloudDataSource
import com.projectmar.filmsapp.data.cloud.model.Result
import com.projectmar.filmsapp.domain.FilmDetails
import com.projectmar.filmsapp.domain.FilmInfo
import com.projectmar.filmsapp.domain.FilmsRepository

class FilmsRepositoryImpl(
    private val filmsCloudDataSource: FilmsCloudDataSource
) : FilmsRepository {

    private val mapper by lazy {
        Mapper()
    }
    override suspend fun getTopList(): LiveData<PagingData<FilmInfo>> {
        val data = filmsCloudDataSource.getTopList()
        return Transformations.map(data) {
            it.map { modelDto ->
                mapper.mapDtoFilmModelToDomain(modelDto)
            }
        }
    }

    override suspend fun getDetails(filmId: String): Result<FilmDetails> {
        return try {
            val res = filmsCloudDataSource.getDetails(filmId)
            Result.Success(mapper.mapDtoFilmDetailsToDomain(res))
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

}