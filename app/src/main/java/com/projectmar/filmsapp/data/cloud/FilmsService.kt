package com.projectmar.filmsapp.data.cloud

import com.projectmar.filmsapp.data.cloud.model.FilmDetailsDto
import com.projectmar.filmsapp.data.cloud.model.FilmsDtoContainer
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsService {
    @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    @GET("api/v2.2/films/top")
    suspend fun getTopList(
        @Query("type") type: String = "TOP_100_POPULAR_FILMS",
        @Query("page") page: Int
    ): FilmsDtoContainer

    @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    @GET("api/v2.2/films/{id}")
    suspend fun detailInfo(@Path("id") id: String): FilmDetailsDto
}