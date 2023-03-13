package com.example.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.e_commerce.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize view binding object
        setContentView(binding.root)


        binding.hamburgericon.setOnClickListener {
            //startActivity(Intent(this, DrawerMenu::class.java))
            drawerLayout.openDrawer(GravityCompat.START)
        }

        drawerLayout = binding.drawerLayout
        navView = binding.navigationView
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_1 -> {
                    startActivity(Intent(this, SignupActivity::class.java))
                    true
                }
                R.id.nav_item_2 -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    true
                }
                R.id.nav_item_3 -> {
                    startActivity(Intent(this, DrawerMenu::class.java))
                    true
                }
                else -> false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}


