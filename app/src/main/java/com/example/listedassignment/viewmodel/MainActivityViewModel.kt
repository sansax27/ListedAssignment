package com.example.listedassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listedassignment.data.NetworkResult
import com.example.listedassignment.data.UIStatus
import com.example.listedassignment.data.models.OpenInAppDataModel
import com.example.listedassignment.data.repository.MainActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainActivityRepository: MainActivityRepository) : ViewModel() {

    private val openInAppDataLiveDataPrivate = MutableLiveData<UIStatus<OpenInAppDataModel>>(UIStatus.Loading)


    val openInAppDataLiveData : LiveData<UIStatus<OpenInAppDataModel>>
    get() = openInAppDataLiveDataPrivate

    init {
        getOpenInAppData()
    }

    private fun getOpenInAppData() = viewModelScope.launch {
        Timber.e("Here 1")
        when(val openInAppData = mainActivityRepository.getOpenInAppData()) {
            is NetworkResult.Success -> openInAppDataLiveDataPrivate.postValue(UIStatus.Success(openInAppData.data))
            is NetworkResult.Failure -> openInAppDataLiveDataPrivate.postValue(UIStatus.Error("${openInAppData.code} ${openInAppData.message}"))
            is NetworkResult.Exception -> openInAppDataLiveDataPrivate.postValue(UIStatus.Error(openInAppData.e.message ?: "Error Occurred"))
        }
    }
}