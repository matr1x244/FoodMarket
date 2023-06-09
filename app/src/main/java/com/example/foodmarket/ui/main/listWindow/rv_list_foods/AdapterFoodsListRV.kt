package com.example.foodmarket.ui.main.listWindow.rv_list_foods

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.domain.data.listFoods.Dishe

class AdapterFoodsListRV : RecyclerView.Adapter<FoodsListViewHolder>() {

    private var foodsList: MutableList<Dishe> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListFoods: List<Dishe>) {
        foodsList.clear()
        foodsList.addAll(newListFoods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_list_foods, parent, false)
        return FoodsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodsListViewHolder, position: Int) {
        holder.bind(foodsList[position])
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

}