package com.example.foodmarket.ui.main.rv_foods

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.domain.data.FoodsCategory

class AdapterFoodsRV : RecyclerView.Adapter<FoodsViewHolder>() {

    private var foodsList: MutableList<FoodsCategory> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListFoods: List<FoodsCategory>) {
        foodsList.clear()
        foodsList.addAll(newListFoods)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_list_foods, parent, false)
        return FoodsViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): FoodsCategory = foodsList[position]

    override fun getItemCount() = foodsList.size
}