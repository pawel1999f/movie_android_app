package com.example.myresponsiveapp

import android.app.Activity
import android.content.res.Configuration
import android.opengl.Visibility
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myresponsiveapp.R
//import com.example.myresponsiveapp.databinding.ActivityMainBinding

import androidx.window.layout.WindowMetricsCalculator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val accountFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
//            this, R.layout.activity_main)

        // ...

        // Replace with a known container that you can safely add a
        // view to where it won't affect the layout and the view
        // won't be replaced.
//        val container: ViewGroup = binding.activity_container as ViewGroup
        val container: ViewGroup = findViewById(R.id.activity_container)

        container.addView(object : View(this) {
            override fun onConfigurationChanged(newConfig: Configuration?) {
                super.onConfigurationChanged(newConfig)
                setLayoutBasedOnWindowSize()
            }
        })

        setLayoutBasedOnWindowSize()

        replaceFrameLayout(homeFragment)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

        findViewById<NavigationView>(R.id.nav_drawer).setNavigationItemSelectedListener { item ->
            changeFragmentBasedOnItemClicked(item)
        }

        findViewById<BottomNavigationView>(R.id.bottom_nav).setOnItemSelectedListener { item ->
            changeFragmentBasedOnItemClicked(item)
        }
    }

    private fun changeFragmentBasedOnItemClicked(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.home_page -> {
                replaceFrameLayout(homeFragment)
                true
            }
            R.id.search_page -> {
                replaceFrameLayout(searchFragment)
                true
            }
            R.id.account_page -> {
                replaceFrameLayout(accountFragment)
                true
            }
            else -> false
        }
    }

    private fun replaceFrameLayout(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
    }

    private fun setLayoutBasedOnWindowSize() {

        val windowWidth = WindowSizeUtils().computeWindowSizeClasses(this)

        when(windowWidth) {
            WindowSizeClass.COMPACT -> setCompactLayout()
            WindowSizeClass.MEDIUM -> setMediumLayout()
            else -> setExpandedLayout()
        }
    }

    private fun setCompactLayout() {
        findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.VISIBLE
        findViewById<NavigationView>(R.id.nav_drawer).visibility = View.GONE
        findViewById<NavigationRailView>(R.id.nav_rail).visibility = View.GONE
    }

    private fun setMediumLayout() {
        findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
        findViewById<NavigationView>(R.id.nav_drawer).visibility = View.GONE
        findViewById<NavigationRailView>(R.id.nav_rail).visibility = View.VISIBLE
    }

    private fun setExpandedLayout() {
        findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
        findViewById<NavigationView>(R.id.nav_drawer).visibility = View.VISIBLE
        findViewById<NavigationRailView>(R.id.nav_rail).visibility = View.GONE
    }
}