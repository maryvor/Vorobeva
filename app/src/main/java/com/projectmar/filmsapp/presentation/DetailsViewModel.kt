package com.projectmar.filmsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectmar.filmsapp.data.cloud.CloudModule
import com.projectmar.filmsapp.data.cloud.FilmsCloudDataSource
import com.projectmar.filmsapp.data.cloud.FilmsRepositoryImpl
import com.projectmar.filmsapp.data.cloud.FilmsService
import com.projectmar.filmsapp.data.cloud.model.Result
import com.projectmar.filmsapp.domain.FilmDetails
import com.projectmar.filmsapp.domain.FilmsRepository
import com.projectmar.filmsapp.domain.GetDetailInfoAboutFilmUseCase
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository: FilmsRepository = FilmsRepositoryImpl(
        FilmsCloudDataSource.Base(CloudModule.Debug().service(FilmsService::class.java))
    )
    private val getDetailInfoAboutFilmUseCase = GetDetailInfoAboutFilmUseCase(repository)

    private val _mutableItem = MutableLiveData<FilmDetails>()
    val item: LiveData<FilmDetails>
        get() = _mutableItem

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    fun getDetails(id: String) {
        _showProgress.value = true
        _showError.value = false
        viewModelScope.launch {
            when (val res = getDetailInfoAboutFilmUseCase.invoke(id)) {
                is Result.Success<*> -> {
                    _mutableItem.value = res.data as FilmDetails
                }
                else -> {
                    _showError.value = true
                }
            }
            _showProgress.postValue(false)
        }
    }
}