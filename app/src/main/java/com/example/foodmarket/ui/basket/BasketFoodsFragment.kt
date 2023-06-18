package com.example.foodmarket.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodmarket.databinding.FragmentBasketBinding
import com.example.foodmarket.ui.basket.rv_basket_foods.AdapterBasketFoodsRV
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class BasketFoodsFragment : Fragment() {

    companion object {
        fun newInstance() = BasketFoodsFragment()
    }

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BasketViewModels by viewModel(named("basket_view_model"))

    private val adapterBasket = AdapterBasketFoodsRV() {
        Toast.makeText(requireActivity(), "${it.sum}", Toast.LENGTH_SHORT).show()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onUpdateBasket(sumBasket++)
            viewModel.getAllBasket()
        }
    }

    private var sumBasket = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodsView()
        testSum()
//        payBasket()
    }

    private fun payBasket() {
        binding.btnPayBasket.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.onDeleteBasketAll()
                binding.btnPayBasket.visibility = View.INVISIBLE
                viewModel.getAllBasket()
                adapterBasket.notifyDataSetChanged()
            }
        }
    }

    private fun testSum() {
        binding.btnPayBasket.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.onUpdateBasket(sumBasket++)
                viewModel.getAllBasket()
                adapterBasket.notifyDataSetChanged()
            }
        }
    }


    private fun foodsView() {
        viewModel.getAllBasket()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.basket.collect {
                adapterBasket.setData(it)
                if (it.isNotEmpty()) {
                    binding.btnPayBasket.visibility = View.VISIBLE
                } else {
                    binding.btnPayBasket.visibility = View.INVISIBLE
                }

            }
        }
        binding.rvViewBasketFoods.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.rvViewBasketFoods.adapter = adapterBasket
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}