package com.example.foodmarket.ui.main.listWindow.detail

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.foodmarket.R
import com.example.foodmarket.databinding.FragmentDetailsFoodsBinding
import com.example.foodmarket.domain.data.listFoods.ListFoods
import com.example.foodmarket.ui.basket.BasketFoodsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class DetailsFoodsFragment() : Fragment() {

    companion object {
        private const val ITEM_ID_DETAILS = "ITEM_ID_DETAILS"

        fun newInstance(item: ListFoods.Dishe?) = DetailsFoodsFragment().apply {
            arguments = Bundle()
            arguments?.putParcelable(ITEM_ID_DETAILS, item)
        }
    }

    private var _binding: FragmentDetailsFoodsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsFoodsViewModel by viewModel(named("foods_detail_view_model"))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsFoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailView()
        buttonClicks()
//        isBlocked()
    }

    private fun detailView() {
        val detail = detailArguments()
        disableBackground(true)
        val weightFood = detail?.weight.toString()
        val spannableWeightFood = SpannableStringBuilder(weightFood)

        val descriptionsFood = detail?.description.toString()
        if (descriptionsFood.lastIndex > 170) {
            val endText = descriptionsFood.substring(0..170) + " ..."
            val spannableDescriptionsFood = SpannableStringBuilder(endText)
            binding.tvDescriptionsFood.text = spannableDescriptionsFood
        } else {
            binding.tvDescriptionsFood.text = descriptionsFood
        }
        spannableWeightFood.insert(0, "• ")
        spannableWeightFood.append("г")
        binding.tvWeightFood.text = spannableWeightFood
        binding.tvNameFood.text = detail?.name
        binding.tvPriceFood.append(detail?.price.toString() + "₽")

        Glide.with(binding.ivBackgroundFoods)
            .load(detail?.image_url)
            .centerInside()
            .into(binding.ivBackgroundFoods)
    }

    private fun detailArguments(): ListFoods.Dishe? {
        return arguments?.getParcelable(ITEM_ID_DETAILS)
    }

    private fun buttonClicks() {
        binding.btnExit.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.btnAddBasket.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, BasketFoodsFragment.newInstance())
                .commit()
        }
    }

    private fun disableBackground(isBlock: Boolean) {
        if (isBlock) {
            binding.hidingScreen.visibility = View.VISIBLE
        } else {
            binding.hidingScreen.visibility = View.INVISIBLE
        }
        binding.hidingScreen.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}