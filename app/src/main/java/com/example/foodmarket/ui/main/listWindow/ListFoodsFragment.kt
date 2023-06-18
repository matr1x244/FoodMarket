package com.example.foodmarket.ui.main.listWindow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodmarket.R
import com.example.foodmarket.databinding.FragmentFoodsListBinding
import com.example.foodmarket.domain.data.categoryFoods.FoodsCategory
import com.example.foodmarket.ui.main.ControllerClickersRV
import com.example.foodmarket.ui.main.listWindow.rv_list_foods.AdapterFoodsListRV
import com.example.foodmarket.ui.main.mainWindow.FoodsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class ListFoodsFragment : Fragment() {

    companion object {
        private const val ITEM_ID_CATEGORY = "ITEM_ID_CATEGORY"
        fun newInstance(item: FoodsCategory?) = ListFoodsFragment().apply {
            arguments = Bundle()
            arguments?.putParcelable(ITEM_ID_CATEGORY, item)
        }
    }

    private var _binding: FragmentFoodsListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FoodsListViewModel by viewModel(named("foods_list_view_model"))
    private val adapterListFoods = AdapterFoodsListRV() {
        controller.openDetailsFragment(it)
    }
    private val controller by lazy { requireActivity() as ControllerClickersRV }
    private val appBarBoxMain: ConstraintLayout? by lazy { requireView().findViewById(R.id.box_appbar_main) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appBarBoxMain?.visibility = View.GONE
        appBar()
        chipMenu()
        viewsShowListFoods()
        loadNewFoods()
    }

    private fun appBar() {
        val args = this.arguments
        val inputData = args?.getString("KEY_NAME")
        binding.tvNameBoxBarFoods.text = inputData.toString()

        binding.ivIconBackMenu.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, FoodsFragment.newInstance())
                .commit()
        }
    }

    private fun chipMenu() {
        binding.chipMenu.setOnCheckedChangeListener { _, selectedId ->
            when (selectedId) {
                R.id.cp_all_menu -> {
                    Toast.makeText(requireActivity(), "cp_all_menu", Toast.LENGTH_SHORT).show()
                }

                R.id.cp_salad -> {
                    Toast.makeText(requireActivity(), "cp_salad", Toast.LENGTH_SHORT).show()
                }

                R.id.cp_rice -> {
                    Toast.makeText(requireActivity(), "cp_rice", Toast.LENGTH_SHORT).show()
                }

                R.id.cp_fish -> {
                    Toast.makeText(requireActivity(), "cp_fish", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun viewsShowListFoods() {
        val nameCategory = nameCategory()
        binding.tvNameBoxBarFoods.text = nameCategory?.name
        viewModel.onShowListFoods()
        binding.rvViewListFoods.layoutManager =
            GridLayoutManager(requireActivity(), 3)
        binding.rvViewListFoods.isSaveEnabled = true
        binding.rvViewListFoods.adapter = adapterListFoods
        progressBar()
    }

    private fun nameCategory(): FoodsCategory? {
        return arguments?.getParcelable(ListFoodsFragment.ITEM_ID_CATEGORY)
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