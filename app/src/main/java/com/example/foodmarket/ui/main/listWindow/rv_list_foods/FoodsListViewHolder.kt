package com.example.foodmarket.ui.main.listWindow.rv_list_foods

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodmarket.databinding.RvItemListFoodsBinding
import com.example.foodmarket.domain.data.listFoods.Dishe

class FoodsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemListFoodsBinding.bind(itemView)

    fun bind(foods: Dishe) {
        Glide.with(binding.ivBackgroundFoods)
            .load(foods.image_url)
            .centerInside()
            .into(binding.ivBackgroundFoods)

        binding.tvNameFoods.text = foods.name
    }

}
