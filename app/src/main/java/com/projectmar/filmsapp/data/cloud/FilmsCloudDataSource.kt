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

//        override suspend fun getFact(number: String): NumberData {
//            val fact = service.fact(number).body() ?: throw IllegalStateException("Service unavailable")
//            return NumberData(number, fact)
//        }
//
//        override suspend fun randomNumberFact(): NumberData {
//            val response = service.randomFact()
//            val body = response.body() ?: throw IllegalStateException("Service unavailable")
//            val headers = response.headers()
//            headers.find { (key, _) ->
//                key == RANDOM_NUMBER_HEADER
//            }?.let { (_, value) ->
//                return NumberData(value, body)
//            }
//            throw IllegalStateException("Service unavailable")
//        }

        override suspend fun getDetails(id: String): FilmDetailsDto {
            val data = service.detailInfo(id)
            return data
        }

        override suspend fun getTopList(): LiveData<PagingData<FilmModelDto>> {
            return Pager(
                config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false),
                pagingSourceFactory = { FilmsPagingSource(service) }
            ).liveData
        }
    }

    companion object {

        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }

}