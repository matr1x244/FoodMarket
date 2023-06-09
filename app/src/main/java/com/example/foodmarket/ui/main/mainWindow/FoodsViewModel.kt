package com.example.foodmarket.ui.main.mainWindow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodmarket.domain.data.RepositoryCategoryFoods
import com.example.foodmarket.domain.data.categoryFoods.ListCategoryFoods
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodsViewModel(private val repository: RepositoryCategoryFoods) : ViewModel() {

    private val _reposListCategoryFoods = MutableLiveData<ListCategoryFoods>()
    val reposListCategoryFoods: MutableLiveData<ListCategoryFoods> = _reposListCategoryFoods

    private val _inProgressFoodsList = MutableLiveData<Boolean>()
    val inProgressFoodsList: LiveData<Boolean> = _inProgressFoodsList

    fun onShowCategory() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("@@@", "No success $throwable !!!")
            _inProgressFoodsList.postValue(true)
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = repository.listCategoryFoods()
            withContext(Dispatchers.Main) {
                delay(500)
                _reposListCategoryFoods.postValue(result)
                _inProgressFoodsList.postValue(false)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}