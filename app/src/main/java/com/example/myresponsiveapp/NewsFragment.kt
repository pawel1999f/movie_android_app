package com.example.myresponsiveapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myresponsiveapp.databinding.FragmentNewsBinding
import com.example.myresponsiveapp.model.News
import com.example.myresponsiveapp.model.news

class NewsFragment : Fragment() {

    lateinit var binding: FragmentNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container: ViewGroup = binding.container3

        container.addView(object : View(requireActivity()) {
            override fun onConfigurationChanged(newConfig: Configuration?) {
                super.onConfigurationChanged(newConfig)
                setLayoutBasedOnWindowSize()
            }
        })

        binding.recyclerView.adapter = NewsAdapter(news)

        setLayoutBasedOnWindowSize()
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
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setMediumLayout() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun setExpandedLayout() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

}