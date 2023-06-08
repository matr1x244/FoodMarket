package com.example.foodmarket.ui.main.categoryWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.databinding.FragmentFoodsListBinding
import com.example.foodmarket.ui.main.categoryWindow.rv_list_foods.AdapterFoodsListRV
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class ListFoodsFragment : Fragment() {

    companion object {
        fun newInstance() = ListFoodsFragment()
    }

    private var _binding: FragmentFoodsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodsListViewModel by viewModel(named("foods_list_view_model"))
    private val adapterListFoods = AdapterFoodsListRV()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewsShowListFoods()
        loadNewFoods()
    }

    private fun viewsShowListFoods() {
        viewModel.onShowListFoods()
        binding.rvViewListFoods.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvViewListFoods.adapter = adapterListFoods
        progressBar()
    }

    private fun progressBar() {
        viewModel.inProgressFoodsList.observe(viewLifecycleOwner) { inProgress ->
            binding.rvViewListFoods.isVisible = !inProgress
            binding.progressBarFoodsList.isVisible = inProgress
        }
    }

    private fun loadNewFoods() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.reposListFoods.collect {
                adapterListFoods.setData(it.dishes)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}