package com.adevperu.template

import android.content.Context


object DisplayUtils {

    fun getCurrentDIP(context: Context): Float {
        return context.resources.displayMetrics.density
    }
}