package com.projectmar.filmsapp.domain

class GetTopListUseCase(private val repository: FilmsRepository) {
    suspend operator fun invoke() = repository.getTopList()
}