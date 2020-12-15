package com.adevperu.template

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs
import kotlin.random.Random

class CardView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val viewMatrix = Matrix()
    private var xCoord: Int = 0
    private var yCoord: Int = 0
    private var center: Int = 0
    private var angle: Float = 0.0f
    private var startAngle: Int = 0

    private var windowWidth: Int = 0
    private var windowHeight: Int = 0

    init {
        inflate(context, R.layout.layout_card, this)
        setupUI()
    }

    private fun setupUI() {
        calculateDisplayDimensions()
        center = windowWidth / 2
        startAngle = randomByRange(-5, 5)
        rotation = startAngle.toFloat()
        setOnTouchListener OnTouchListener@{ _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                }

                MotionEvent.ACTION_MOVE -> {
                    xCoord = motionEvent.rawX.toInt()
                    yCoord = motionEvent.rawY.toInt()

                    x = (xCoord - center + 40).toFloat()
                    y = (yCoord - windowHeight / 2).toFloat()

                    angle = if (xCoord >= center) {
                        ((xCoord - center) * (Math.PI / 32)).toFloat()

                    } else {
                        ((xCoord - center) * (Math.PI / 32)).toFloat()
                    }
                    rotation = angle
                }

                MotionEvent.ACTION_UP -> {

                    if (abs(angle) > 40) {
                        //listener?.removeCard(this@CardFragment)
                        return@OnTouchListener true
                    }
                    xCoord = motionEvent.rawX.toInt()
                    yCoord = motionEvent.rawY.toInt()

                    x = 0f//20 * DisplayUtils.getCurrentDIP(activity)
                    y = 0f//10 * DisplayUtils.getCurrentDIP(activity)
                    rotation = startAngle.toFloat()
                    angle = 0.0f
                }
            }
            true
        }
    }

    private fun calculateDisplayDimensions() {
        val metrics = DisplayMetrics()
        //context.windowManager?.defaultDisplay?.getMetrics(metrics)
        //windowWidth = metrics.widthPixels
        //windowHeight = metrics.heightPixels
    }

    private fun randomByRange(min: Int, max: Int): Int {
        val range = max - min + 1
        return Random.nextInt(range) + min
    }
}