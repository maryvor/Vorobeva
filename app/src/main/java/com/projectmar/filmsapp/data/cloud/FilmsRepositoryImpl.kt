package com.projectmar.filmsapp.data.cloud

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingData
import androidx.paging.map
import com.projectmar.filmsapp.domain.FilmDetails
import com.projectmar.filmsapp.domain.FilmInfo
import com.projectmar.filmsapp.domain.FilmsRepository

class FilmsRepositoryImpl(
    private val filmsCloudDataSource: FilmsCloudDataSource
) : FilmsRepository {

    override suspend fun getTopList(): LiveData<PagingData<FilmInfo>> {
        val data = filmsCloudDataSource.getTopList()
        return Transformations.map(data){
            it.map {modelDto ->
                Mapper().mapDtoFilmModelToDomain(modelDto)
            }
        }
    }

    override suspend fun getDetails(filmId: String): FilmDetails {
        return Mapper().mapDtoFilmDetailsToDomain(filmsCloudDataSource.getDetails(filmId))
    }

    override suspend fun addToFavourite(film: FilmInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavourite(filmId: String) {
        TODO("Not yet implemented")
    }


}