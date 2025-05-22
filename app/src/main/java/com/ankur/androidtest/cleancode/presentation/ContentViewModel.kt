package com.ankur.androidtest.cleancode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankur.androidtest.cleancode.domain.model.DomainModel
import com.ankur.androidtest.cleancode.domain.usecases.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(val contentUseCase: ContentUseCase):ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun getContent(){
        contentUseCase()
            .onStart {
                _uiState.update { UiState(isLoading = true) }
            }.onEach {result ->
                if (result.isSuccess){
                    _uiState.update { UiState(data = result.getOrThrow()) }
                }else{
                    _uiState.update { UiState(error = result.exceptionOrNull()?.message.toString()) }
                }
            }.catch {error->
                _uiState.update {
                    UiState(error = error.message.toString())
                }
            }.launchIn(viewModelScope)
    }
}

data class UiState(
    val isLoading:Boolean = false,
    val error:String = "",
    val data:List<DomainModel>?=null
)