package com.example.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.e_commerce.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private val handler = Handler()
    private var autoScrollRunnable: Runnable? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

// Check if user is already logged in
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            // User is already logged in, redirect to desired activity
            startActivity(Intent(this, DrawerMenu::class.java))
            finish() // Finish the current activity to prevent user from going back to login/signup screens
        } else {
            // User is not logged in, continue with MainActivity setup
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Your existing code goes here
            binding = ActivityMainBinding.inflate(layoutInflater) // Initialize view binding object
            setContentView(binding.root)


            //The hamburger Icon to show the menu options
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


            //The sliding images in the recycle viewer

            // Define a list of image resources
            val imageList = listOf(
                R.drawable.appliances,
                R.drawable.beauty,
                R.drawable.clothings,
                R.drawable.groceries,
                R.drawable.soundsystem
            )
            autoScrollRunnable = object : Runnable {
                override fun run() {
                    binding.viewPager.currentItem =
                        (binding.viewPager.currentItem + 1) % imageList.size
                    handler.postDelayed(this, 2000)
                }
            }
            handler.postDelayed(autoScrollRunnable!!, 2000)

// Set up the ViewPager2 with the Adapter
            binding.viewPager.adapter = ImageAdapter(imageList)

// Use a Timer to automatically scroll to the next image every 2 seconds
            val timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        binding.viewPager.currentItem = (binding.viewPager.currentItem + 1) % imageList.size
                    }
                }
            }, 0, 2000)


// Add a listener to cancel the Timer when the user manually changes the current item
            binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val description = getImageDescription(position)
                    binding.imageDescription.text = description
                }

                // Helper function to get the description for a given image position
                private fun getImageDescription(position: Int): String {
                    return when (position) {
                        0 -> "Appliances"
                        1 -> "Beauty"
                        2 -> "Clothings"
                        3 -> "Groceries"
                        4 -> "Sound system"
                        else -> ""
                    }
                }
            })

            // binding the next and previous icons

            val previous = binding.previous
            val next = binding.next
            val viewPager = binding.viewPager

            previous.setOnClickListener {
                if (viewPager.currentItem > 0) {
                    viewPager.currentItem = viewPager.currentItem - 1
                }
            }

            next.setOnClickListener {
                if (viewPager.currentItem < (viewPager.adapter?.itemCount ?: 0)) {
                    viewPager.currentItem = viewPager.currentItem + 1
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        autoScrollRunnable?.let { handler.removeCallbacks(it) }
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


