package com.projectmar.filmsapp.data.cloud

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.projectmar.filmsapp.data.cloud.model.FilmModelDto
import retrofit2.HttpException
import java.io.IOException

class FilmsPagingSource(private val filmsService: FilmsService) :
    PagingSource<Int, FilmModelDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmModelDto> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = filmsService.getTopList(page = page)
            LoadResult.Page(
                response.films, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.films.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FilmModelDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object{
        private const val DEFAULT_PAGE_INDEX = 1
    }


}