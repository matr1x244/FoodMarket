package com.example.foodmarket.ui.main.mainWindow.rv_category_foods

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.domain.data.categoryFoods.FoodsCategory

class AdapterCategoryFoodsRV : RecyclerView.Adapter<FoodsViewHolder>() {

    private var foodsList: MutableList<FoodsCategory> = mutableListOf()

    private lateinit var clickListener: OnItemClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newCategoryFoods: List<FoodsCategory>) {
        foodsList.clear()
        foodsList.addAll(newCategoryFoods)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_category_foods, parent, false)
        return FoodsViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        holder.bind(foodsList[position])
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }
}