package com.adevperu.template

import android.content.Context
import kotlin.random.Random

fun Context.density() = resources.displayMetrics.density

/*
    private fun calculateDisplayDimensions() {
        val metrics = DisplayMetrics()
        (context as Activity).windowManager?.defaultDisplay?.getMetrics(metrics)
        windowWidth = metrics.widthPixels
        windowHeight = metrics.heightPixels
    }
 */

/*
private fun randomByRange(min: Int, max: Int): Int {
    val range = max - min + 1
    return Random.nextInt(range) + min
}
*/
fun Int.randomByRange(max: Int): Int {
    val range = max - this + 1
    return Random.nextInt(range) + this
}