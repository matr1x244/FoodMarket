package com.example.foodmarket.ui.main.categoryWindow.rv_list_foods

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.databinding.RvItemListFoodsBinding
import com.example.foodmarket.domain.data.listFoods.Dishe

class FoodsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemListFoodsBinding.bind(itemView)

    fun bind(foods: Dishe) {
        binding.tvNameFoods.text = foods.description
    }

}
