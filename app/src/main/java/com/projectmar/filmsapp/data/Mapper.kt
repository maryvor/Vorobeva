package com.projectmar.filmsapp.data

import com.projectmar.filmsapp.data.cloud.model.FilmDetailsDto
import com.projectmar.filmsapp.data.cloud.model.FilmModelDto
import com.projectmar.filmsapp.domain.FilmDetails
import com.projectmar.filmsapp.domain.FilmInfo

class Mapper {

    fun mapDtoFilmModelToDomain(filmDto: FilmModelDto): FilmInfo {
        val listGenres = mutableListOf<String>()
        filmDto.genres.forEach {
            listGenres.add(it.genre)
        }
        val listCountries = mutableListOf<String>()
        filmDto.countries.forEach {
            listCountries.add(it.country)
        }
        return FilmInfo(
            id = filmDto.id,
            name = filmDto.name ?: "",
            genres = listGenres.joinToString(", "),
            countries = listCountries.joinToString(", "),
            year = filmDto.year ?: "",
            isFavourite = false,
            smallImg = filmDto.posterUrlPreview

        )
    }

    fun mapDtoFilmDetailsToDomain(filmDetailsDto: FilmDetailsDto): FilmDetails {
        val listGenres = mutableListOf<String>()
        filmDetailsDto.genres.forEach {
            listGenres.add(it.genre)
        }
        val listCountries = mutableListOf<String>()
        filmDetailsDto.countries.forEach {
            listCountries.add(it.country)
        }

        return FilmDetails(
            id = filmDetailsDto.id,
            name = filmDetailsDto.name ?: "",
            description = filmDetailsDto.description ?: "",
            genres = listGenres.joinToString(", "),
            countries = listCountries.joinToString(", "),
            year = filmDetailsDto.year ?: "",
            banner = filmDetailsDto.posterUrl

        )
    }
}