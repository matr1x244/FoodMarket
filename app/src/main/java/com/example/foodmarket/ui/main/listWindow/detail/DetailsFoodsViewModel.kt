package com.example.foodmarket.ui.main.listWindow.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

class DetailsFoodsViewModel() : ViewModel() {

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}