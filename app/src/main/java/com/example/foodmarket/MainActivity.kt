package com.example.foodmarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodmarket.ui.main.listWindow.ListFoodsFragment
import com.example.foodmarket.ui.main.mainWindow.FoodsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
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
                        .replace(R.id.main_fragment_container, ListFoodsFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                }
                R.id.search_menu -> {
                    Toast.makeText(this, "search_menu", Toast.LENGTH_SHORT).show()
                }
                R.id.basket_menu -> {
                    Toast.makeText(this, "basket_menu", Toast.LENGTH_SHORT).show()
                }
                R.id.profile_menu -> {
                    Toast.makeText(this, "profile_menu", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.foods_menu
    }
}