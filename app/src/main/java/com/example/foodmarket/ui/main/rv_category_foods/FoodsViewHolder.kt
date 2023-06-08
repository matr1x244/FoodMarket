package com.example.foodmarket.ui.main.rv_category_foods

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodmarket.databinding.RvItemCategoryFoodsBinding
import com.example.foodmarket.domain.data.categoryFoods.FoodsCategory

class FoodsViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemCategoryFoodsBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            listener.onItemClick(1)
        }
    }

    fun bind(foods: FoodsCategory) {
        Glide.with(binding.ivBackgroundFoods)
            .load(foods.image_url)
            .centerCrop()
            .into(binding.ivBackgroundFoods)

        binding.tvNameFoods.text = foods.name
    }

}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}