package com.example.foodmarket.ui.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodmarket.date.local.BasketEntity
import com.example.foodmarket.date.local.RepositoryBasket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class BasketViewModels(private val repository: RepositoryBasket) : ViewModel() {

    private val _basket = MutableSharedFlow<List<BasketEntity>>()
    val basket: MutableSharedFlow<List<BasketEntity>> = _basket

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

    fun onUpdateBasket(sum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBasket(sum)
        }
    }

    fun onDeleteBasket() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBasket()
        }
    }

    fun getAllBasket() {
        viewModelScope.launch(Dispatchers.IO) {
            _basket.emit(repository.getAllBasket())
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}