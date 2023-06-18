package com.example.foodmarket.ui.main.mainWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.databinding.FragmentFoodsBinding
import com.example.foodmarket.ui.main.ControllerClickersRV
import com.example.foodmarket.ui.main.mainWindow.rv_category_foods.AdapterCategoryFoodsRV
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
    private val adapterFoods = AdapterCategoryFoodsRV() {
        controller.openListFragment(it)
    }
    private val controller by lazy { requireActivity() as ControllerClickersRV }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewShowCategoryFoods()
    }

    private fun viewShowCategoryFoods() {
        viewModel.onShowCategory()
        viewModel.reposListCategoryFoods.observe(viewLifecycleOwner) {
            adapterFoods.setData(it.сategories)
        }
        binding.rvViewCategoryFoods.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
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