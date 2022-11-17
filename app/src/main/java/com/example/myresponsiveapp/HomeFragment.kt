package com.example.myresponsiveapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.window.layout.WindowMetricsCalculator
import com.example.myresponsiveapp.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val newsFragment = NewsFragment()
    private val popularMoviesFragment = PopularMoviesFragment()
    private val topMoviesFragment = TopMoviesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container: ViewGroup = binding.container as ViewGroup

        container.addView(object : View(context) {
            override fun onConfigurationChanged(newConfig: Configuration?) {
                super.onConfigurationChanged(newConfig)
                setLayoutBasedOnWindowSize()
            }
        })

        setLayoutBasedOnWindowSize()

        replaceFrameLayout(NewsFragment())


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        replaceFrameLayout(newsFragment)
                    }
                    1 -> {
                        replaceFrameLayout(popularMoviesFragment)
                    }
                    2 -> {
                        replaceFrameLayout(topMoviesFragment)
                    }
                    else -> {}
                }

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }

    private fun replaceFrameLayout(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container2, fragment)
            .commit();
    }

    private fun setLayoutBasedOnWindowSize() {

        val windowWidth = WindowSizeUtils().computeWindowSizeClasses(requireActivity())

        when(windowWidth) {
            WindowSizeClass.COMPACT -> setCompactLayout()
            WindowSizeClass.MEDIUM -> setMediumLayout()
            else -> setExpandedLayout()
        }
    }

    private fun setCompactLayout() {
        binding.tabLayout.visibility = View.VISIBLE
        binding.fragmentContainer2.visibility = View.VISIBLE
        binding.wideContainer.visibility = View.GONE
    }

    private fun setMediumLayout() {
        binding.tabLayout.visibility = View.GONE
        binding.fragmentContainer2.visibility = View.GONE
        binding.wideContainer.visibility = View.VISIBLE
    }

    private fun setExpandedLayout() {
        binding.tabLayout.visibility = View.GONE
        binding.fragmentContainer2.visibility = View.GONE
        binding.wideContainer.visibility = View.VISIBLE
    }
}