package com.example.foodmarket.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.databinding.FragmentFoodsBinding
import com.example.foodmarket.ui.main.rv_foods.AdapterFoodsRV
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

    private val viewModel: FoodsViewModel by viewModel(named("foods_view_model"))
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
        viewShowListFoods()
        loadNewFoodsList()
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

    private fun viewShowListFoods() {
        viewModel.onShowList()
        binding.rvViewListFoods.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvViewListFoods.adapter = adapterFoods
        progressBar()
    }

    private fun progressBar() {
        viewModel.inProgressFoodsList.observe(viewLifecycleOwner) { inProgress ->
            binding.rvViewListFoods.isVisible = !inProgress
            binding.progressBarFoodsList.isVisible = inProgress
        }
    }

    private fun loadNewFoodsList() {
        viewModel.reposListCategoryFoods.observe(viewLifecycleOwner) {
            adapterFoods.setData(it.сategories)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}