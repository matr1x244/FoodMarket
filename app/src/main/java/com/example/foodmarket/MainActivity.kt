package com.example.foodmarket

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmarket.domain.data.categoryFoods.FoodsCategory
import com.example.foodmarket.domain.data.listFoods.ListFoods
import com.example.foodmarket.ui.basket.BasketFoodsFragment
import com.example.foodmarket.ui.main.ControllerClickersRV
import com.example.foodmarket.ui.main.listWindow.ListFoodsFragment
import com.example.foodmarket.ui.main.listWindow.detail.DetailsFoodsFragment
import com.example.foodmarket.ui.main.mainWindow.FoodsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ControllerClickersRV {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, FoodsFragment.newInstance())
                .commitNow()
        }

        bottomNavMenu()
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
