package com.example.foodmarket.ui.main.listWindow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodmarket.domain.data.RepositoryListFoods
import com.example.foodmarket.domain.data.listFoods.ListFoods
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodsListViewModel(private val repository: RepositoryListFoods) : ViewModel() {

    private val _reposListFoods = MutableSharedFlow<ListFoods>()
    val reposListFoods: MutableSharedFlow<ListFoods> = _reposListFoods

    private val _inProgressFoodsList = MutableLiveData<Boolean>()
    val inProgressFoodsList: LiveData<Boolean> = _inProgressFoodsList

    fun onShowListFoods() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("@@@", "No success $throwable !!!")
            _inProgressFoodsList.postValue(true)
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = repository.listFoods()
            withContext(Dispatchers.Main) {
                delay(1_000)
                _reposListFoods.emit(result)
                _inProgressFoodsList.postValue(false)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}