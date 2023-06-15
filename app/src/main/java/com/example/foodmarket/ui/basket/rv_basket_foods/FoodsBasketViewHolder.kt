package com.example.foodmarket.ui.basket.rv_basket_foods

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.databinding.RvItemBasketFoodsBinding
import com.example.foodmarket.date.local.BasketEntity

class FoodsBasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemBasketFoodsBinding.bind(itemView)

    fun bind(foods: BasketEntity, listener: BasketEntity.() -> Unit) {
//        Glide.with(binding.ivBackgroundFoods)
//            .load(foods.image_url)
//            .centerCrop()
//            .into(binding.ivBackgroundFoods)

        binding.tvRoom.text = foods.name

        binding.root.setOnClickListener {
            listener.invoke(foods)
        }
    }
}
