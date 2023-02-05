package com.projectmar.filmsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.projectmar.filmsapp.data.cloud.CloudModule
import com.projectmar.filmsapp.data.cloud.FilmsCloudDataSource
import com.projectmar.filmsapp.data.cloud.FilmsRepositoryImpl
import com.projectmar.filmsapp.data.cloud.FilmsService
import com.projectmar.filmsapp.domain.FilmInfo
import com.projectmar.filmsapp.domain.FilmsRepository
import com.projectmar.filmsapp.domain.GetTopListUseCase
import kotlinx.coroutines.launch

class FilmsViewModel : ViewModel() {

    private val repository: FilmsRepository = FilmsRepositoryImpl(
        FilmsCloudDataSource.Base(CloudModule.Debug().service(FilmsService::class.java))
    )
    private val getTopListUseCase = GetTopListUseCase(repository)

    private var _topList = MutableLiveData<PagingData<FilmInfo>>()
    val topList: LiveData<PagingData<FilmInfo>>
        get() = _topList

    fun loadTop() {
        viewModelScope.launch() {
            _topList = getTopListUseCase.invoke() as MutableLiveData<PagingData<FilmInfo>>

        }
    }
}