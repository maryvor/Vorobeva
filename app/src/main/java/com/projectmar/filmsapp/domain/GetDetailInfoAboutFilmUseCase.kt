package com.projectmar.filmsapp.domain

import com.projectmar.filmsapp.data.cloud.model.Result

class GetDetailInfoAboutFilmUseCase(private val repository: FilmsRepository) {
    suspend operator fun invoke(filmId: String): Result<FilmDetails> =
        repository.getDetails(filmId)

}
