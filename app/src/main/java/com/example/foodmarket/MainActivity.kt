package com.example.foodmarket

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.foodmarket.domain.data.categoryFoods.FoodsCategory
import com.example.foodmarket.domain.data.listFoods.ListFoods
import com.example.foodmarket.ui.basket.BasketFoodsFragment
import com.example.foodmarket.ui.main.ControllerClickersRV
import com.example.foodmarket.ui.main.detailWindow.DetailsFoodsFragment
import com.example.foodmarket.ui.main.listWindow.ListFoodsFragment
import com.example.foodmarket.ui.main.mainWindow.FoodsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity(), ControllerClickersRV {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, FoodsFragment.newInstance())
                .commitNow()
        }

        appBar()
        bottomNavMenu()
    }

    private fun appBar() {
        val tvDate = findViewById<AppCompatTextView>(R.id.tv_date)
        val tvGeoCityName = findViewById<AppCompatTextView>(R.id.tv_geo_city_name)
        val ivProfilePhoto = findViewById<AppCompatImageView>(R.id.iv_profile_photo)
        val date =
            SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH).format(Calendar.getInstance().time)
        tvDate.text = date
        tvGeoCityName.text = "Санкт-Петербург"
        ivProfilePhoto.setOnClickListener {
            Toast.makeText(this, "Сменить аватар", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bottomNavMenu() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.foods_menu -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, FoodsFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                }

                R.id.search_menu -> {
                    Toast.makeText(this, "search_menu", Toast.LENGTH_SHORT).show()
                }

                R.id.basket_menu -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_fragment_container, BasketFoodsFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                }

                R.id.profile_menu -> {
                    Toast.makeText(this, "profile_menu", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.foods_menu
    }

    override fun openDetailsFragment(item: ListFoods.Dishe) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, DetailsFoodsFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
    }

    override fun openListFragment(item: FoodsCategory) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, ListFoodsFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
    }

}
