package com.example.foodmarket.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodmarket.databinding.FragmentFoodsBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale


class FoodsFragment: Fragment() {

    companion object {
        fun newInstance() = FoodsFragment()
    }

    private var _binding: FragmentFoodsBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun defaultData() {
        val date = SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH).format(Calendar.getInstance().time)
        binding.tvDate.text = date
        binding.tvGeoCityName.text = "Санкт-Петербург"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}