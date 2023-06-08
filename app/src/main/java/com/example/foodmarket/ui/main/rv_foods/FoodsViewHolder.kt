package com.example.foodmarket.ui.main.rv_foods

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodmarket.databinding.RvItemListFoodsBinding
import com.example.foodmarket.domain.data.FoodsCategory

class FoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemListFoodsBinding.bind(itemView)

    fun bind(foods: FoodsCategory) {
        Glide.with(binding.ivBackgroundFoods)
            .load(foods.image_url)
            .centerCrop()
            .into(binding.ivBackgroundFoods)

        binding.tvNameFoods.text = foods.name
    }

}