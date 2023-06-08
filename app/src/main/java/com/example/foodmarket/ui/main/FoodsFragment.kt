package com.example.foodmarket.ui.main

import android.graphics.Color
import android.media.effect.EffectFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.R
import com.example.foodmarket.databinding.FragmentFoodsBinding
import com.example.foodmarket.ui.main.categoryWindow.ListFoodsFragment
import com.example.foodmarket.ui.main.rv_category_foods.AdapterFoodsRV
import com.example.foodmarket.ui.main.rv_category_foods.FoodsViewHolder
import com.example.foodmarket.ui.main.rv_category_foods.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class FoodsFragment : Fragment() {

    companion object {
        fun newInstance() = FoodsFragment()
    }

    private var _binding: FragmentFoodsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodsViewModel by viewModel(named("foods_category_view_model"))
    private val adapterFoods = AdapterFoodsRV()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        defaultData()
        viewShowCategoryFoods()
    }

    private fun defaultData() {
        val date =
            SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH).format(Calendar.getInstance().time)
        binding.tvDate.text = date
        binding.tvGeoCityName.text = "Санкт-Петербург"
        binding.ivProfilePhoto.setOnClickListener {
            Toast.makeText(requireActivity(), "Сменить аватар", Toast.LENGTH_SHORT).show()
        }
    }
    private fun viewShowCategoryFoods() {
        viewModel.onShowCategory()
        viewModel.reposListCategoryFoods.observe(viewLifecycleOwner) {
            adapterFoods.setData(it.сategories)
        }
        binding.rvViewCategoryFoods.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        adapterFoods.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                when(position){
                    1 -> {
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_fragment_container, ListFoodsFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                }
            }
        })
        binding.rvViewCategoryFoods.adapter = adapterFoods
        progressBar()
    }

    private fun progressBar() {
        viewModel.inProgressFoodsList.observe(viewLifecycleOwner) { inProgress ->
            binding.rvViewCategoryFoods.isVisible = !inProgress
            binding.progressBarFoodsCategory.isVisible = inProgress
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}