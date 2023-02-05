package com.projectmar.filmsapp.data.cloud

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.projectmar.filmsapp.data.cloud.model.FilmDetailsDto
import com.projectmar.filmsapp.data.cloud.model.FilmModelDto

interface FilmsCloudDataSource {

    suspend fun getDetails(id: String): FilmDetailsDto

    suspend fun getTopList(): LiveData<PagingData<FilmModelDto>>

    class Base(private val service: FilmsService) : FilmsCloudDataSource {

        override suspend fun getDetails(id: String): FilmDetailsDto {
            return service.detailInfo(id)
        }

        override suspend fun getTopList(): LiveData<PagingData<FilmModelDto>> {
            return Pager(
                config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false, maxSize = MAX_SIZE),
                pagingSourceFactory = { FilmsPagingSource(service) }
            ).liveData
        }
    }

    companion object {

        private const val DEFAULT_PAGE_SIZE = 20
        private const val MAX_SIZE = 100
    }

}