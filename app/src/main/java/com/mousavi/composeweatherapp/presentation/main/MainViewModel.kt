package com.mousavi.composeweatherapp.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel(){

    private var _state = mutableStateOf(0)
    val state: State<Int> = _state

    fun onEvent(index: Int){
        _state.value = index
    }

}