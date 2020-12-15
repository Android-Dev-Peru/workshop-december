package com.adevperu.template

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs
import kotlin.random.Random

/**
 * @author Eduardo Medina
 */
class CardView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var xCoordinate: Int = 0
    private var yCoordinate: Int = 0
    private var center: Int = 0
    private var angle: Float = 0.0f
    private var startAngle: Int = 0

    private var windowWidth: Int = 0
    private var windowHeight: Int = 0

    private var listener: (CardView) -> Unit = {}

    fun addListener(callback: (view: CardView) -> Unit) {
        listener = callback
    }

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
                MotionEvent.ACTION_DOWN -> { }

                MotionEvent.ACTION_MOVE -> {
                    xCoordinate = motionEvent.rawX.toInt()
                    yCoordinate = motionEvent.rawY.toInt()

                    x = (xCoordinate - center + 40).toFloat()
                    y = (yCoordinate - windowHeight / 2).toFloat()

                    angle = if (xCoordinate >= center) {
                        ((xCoordinate - center) * (Math.PI / 32)).toFloat()

                    } else {
                        ((xCoordinate - center) * (Math.PI / 32)).toFloat()
                    }
                    rotation = angle
                }

                MotionEvent.ACTION_UP -> {

                    if (abs(angle) > MAX_ANGLE) {
                        listener(this@CardView)
                        return@OnTouchListener true
                    }
                    xCoordinate = motionEvent.rawX.toInt()
                    yCoordinate = motionEvent.rawY.toInt()

                    x = 20 * DisplayUtils.getCurrentDIP(context)
                    y = 10 * DisplayUtils.getCurrentDIP(context)
                    rotation = startAngle.toFloat()
                    angle = 0.0f
                }
            }
            true
        }
    }

    private fun calculateDisplayDimensions() {
        val metrics = DisplayMetrics()
        (context as Activity).windowManager?.defaultDisplay?.getMetrics(metrics)
        windowWidth = metrics.widthPixels
        windowHeight = metrics.heightPixels
    }

    private fun randomByRange(min: Int, max: Int): Int {
        val range = max - min + 1
        return Random.nextInt(range) + min
    }

    companion object {
        const val MAX_ANGLE = 40
    }
}
