package com.example.myresponsiveapp

import android.app.Activity
import android.content.Context
import androidx.window.layout.WindowMetricsCalculator

enum class WindowSizeClass { COMPACT, MEDIUM, EXPANDED }

class WindowSizeUtils {
    fun computeWindowSizeClasses(activity: Activity): WindowSizeClass {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(activity)

        val widthDp = metrics.bounds.width() /
                activity.resources.displayMetrics.density
        val widthWindowSizeClass = when {
            widthDp < 600f -> WindowSizeClass.COMPACT
            widthDp < 840f -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }

        val heightDp = metrics.bounds.height() /
                activity.resources.displayMetrics.density
        val heightWindowSizeClass = when {
            heightDp < 480f -> WindowSizeClass.COMPACT
            heightDp < 900f -> WindowSizeClass.MEDIUM
            else -> WindowSizeClass.EXPANDED
        }

        // Use widthWindowSizeClass and heightWindowSizeClass.

//        print(widthDp)
//        print(widthWindowSizeClass)
//        print(heightDp)
//        print(heightWindowSizeClass)
//        if(widthWindowSizeClass == WindowSizeClass.COMPACT)
//            setCompactLayout()
//        else if(widthWindowSizeClass == WindowSizeClass.COMPACT)
//            setMediumLayout()
//        else
//            setExpandedLayout()

        return widthWindowSizeClass;
    }
}