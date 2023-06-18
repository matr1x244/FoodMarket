package com.example.foodmarket.ui.basket.rv_basket_foods

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodmarket.databinding.RvItemBasketFoodsBinding
import com.example.foodmarket.date.local.BasketEntity

class FoodsBasketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemBasketFoodsBinding.bind(itemView)
    fun bind(foods: BasketEntity, listener: BasketEntity.() -> Unit) {
        Glide.with(binding.ivFood)
            .load(foods.image_url)
            .centerInside()
            .into(binding.ivFood)

        binding.tvNameFood.text = foods.name.toString()
        binding.tvPrice.text = foods.price + " ₽" + " • " + foods.weight + "г"
        binding.tvSum.text = foods.sum.toString()
        binding.root.setOnClickListener {
            listener.invoke(foods)
        }
    }
}
