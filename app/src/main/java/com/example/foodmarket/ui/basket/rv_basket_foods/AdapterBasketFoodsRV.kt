package com.example.foodmarket.ui.basket.rv_basket_foods

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.date.local.BasketEntity

class AdapterBasketFoodsRV(private val itemClick: (BasketEntity) -> Unit) :
    RecyclerView.Adapter<FoodsBasketViewHolder>() {

    private var basketList = mutableListOf<BasketEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<BasketEntity>) {
        basketList.clear()
        basketList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsBasketViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_basket_foods, parent, false)
        return FoodsBasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodsBasketViewHolder, position: Int) {
        holder.bind(getItem(position), itemClick)
    }

    private fun getItem(position: Int): BasketEntity {
        return basketList[position]
    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}