package com.example.foodmarket.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodmarket.date.local.BasketEntity
import com.example.foodmarket.date.local.RepositoryBasket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BasketViewModels(private val repository: RepositoryBasket) : ViewModel() {

    private val _basket = MutableLiveData<List<BasketEntity>>()
    val basket: LiveData<List<BasketEntity>> = _basket

    fun onSaveBasket(
        name: String,
        image_url: String,
        price: String,
        weight: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveBasket(name, image_url, price, weight)
        }
    }


    fun onDeleteBasket() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBasket()
        }
    }

    fun getBasket() {
        viewModelScope.launch(Dispatchers.IO) {
            _basket.postValue(repository.getBasket())
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}