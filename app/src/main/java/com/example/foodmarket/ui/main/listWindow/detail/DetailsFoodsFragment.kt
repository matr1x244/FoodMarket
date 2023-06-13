package com.example.foodmarket.ui.main.listWindow.detail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodmarket.databinding.FragmentDetailsFoodsBinding
import com.example.foodmarket.domain.data.listFoods.ListFoods

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsFoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun initView() {
        val detail = detail()
        binding.settext.text = detail?.description
        binding.settext.movementMethod = ScrollingMovementMethod()
    }

    private fun detail(): ListFoods.Dishe? {
        return arguments?.getParcelable(ITEM_ID_DETAILS)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}