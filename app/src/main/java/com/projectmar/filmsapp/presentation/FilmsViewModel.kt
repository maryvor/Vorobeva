package com.projectmar.filmsapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.projectmar.filmsapp.data.cloud.CloudModule
import com.projectmar.filmsapp.data.cloud.FilmsCloudDataSource
import com.projectmar.filmsapp.data.cloud.FilmsRepositoryImpl
import com.projectmar.filmsapp.data.cloud.FilmsService
import com.projectmar.filmsapp.domain.FilmDetails
import com.projectmar.filmsapp.domain.FilmInfo
import com.projectmar.filmsapp.domain.FilmsRepository
import kotlinx.coroutines.launch

class FilmsViewModel : ViewModel() {

    private var repository: FilmsRepository = FilmsRepositoryImpl(
        FilmsCloudDataSource.Base(CloudModule.Debug().service(FilmsService::class.java))
    )

    private var mutableLiveData = MutableLiveData<PagingData<FilmInfo>>()
    val lifeData: LiveData<PagingData<FilmInfo>>
        get() = mutableLiveData

    private val _mutableItem = MutableLiveData<FilmDetails>()
    val item: LiveData<FilmDetails>
        get() = _mutableItem

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = _showError

    fun init() {
        _showProgress.value = true
        viewModelScope.launch() {

            mutableLiveData = repository.getTopList() as MutableLiveData<PagingData<FilmInfo>>
            _showProgress.postValue(false)
            Log.d("lifeData", "${lifeData.value}")

        }


    }

    fun getDetails(id: String) {
        _showProgress.value = true
        viewModelScope.launch() {
            _mutableItem.value = repository.getDetails(id)
            _showProgress.postValue(false)
        }
    }

}