package com.adevperu.template

import android.content.Context


object DisplayUtils {

    fun getCurrentDIP(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    private fun getDip(scale: Float, pixel: Int): Int {
        return (pixel * scale + 0.5f).toInt()
    }


    fun getCurrentInfo(context: Context): DisplayInfo? {
        val d = context.resources.displayMetrics
        return DisplayInfo(
            getDip(getCurrentDIP(context), d.widthPixels),
            getDip(getCurrentDIP(context), d.heightPixels)
        )
    }
}