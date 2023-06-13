package com.example.foodmarket.ui.main.mainWindow.rv_category_foods

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodmarket.databinding.RvItemCategoryFoodsBinding
import com.example.foodmarket.domain.data.categoryFoods.FoodsCategory
import com.example.foodmarket.domain.data.listFoods.ListFoods

class FoodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = RvItemCategoryFoodsBinding.bind(itemView)

    fun bind(foods: FoodsCategory, listener: FoodsCategory.() -> Unit) {
        Glide.with(binding.ivBackgroundFoods)
            .load(foods.image_url)
            .centerCrop()
            .into(binding.ivBackgroundFoods)

        binding.tvNameFoods.text = foods.name
        binding.root.setOnClickListener {
            listener.invoke(foods)
        }
    }

}