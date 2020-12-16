package com.adevperu.template

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs

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
        startAngle = (-15).randomByRange(15)

        rotation = startAngle.toFloat()

        setOnTouchListener OnTouchListener@{ _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                }

                MotionEvent.ACTION_MOVE -> {
                    xCoordinate = motionEvent.rawX.toInt()
                    yCoordinate = motionEvent.rawY.toInt()

                    x = (xCoordinate - center + OFFSET_X).toFloat()
                    y = (yCoordinate - windowHeight / 2).toFloat()

                    angle = if (xCoordinate >= center) {
                        ((xCoordinate - center) * (Math.PI / ROTATION_ANGLE)).toFloat()

                    } else {
                        ((xCoordinate - center) * (Math.PI / ROTATION_ANGLE)).toFloat()
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

                    x = DEFAULT_OFFSET_X * context.density()
                    y = DEFAULT_OFFSET_Y * context.density()
                    rotation = startAngle.toFloat()
                    angle = 0.0f
                }
            }
            true
        }
    }

    private fun calculateDisplayDimensions() {
        windowWidth = context.viewWidthPixel()
        windowHeight = context.viewHeightPixel()
    }

    companion object {
        const val MAX_ANGLE = 40
        const val ROTATION_ANGLE = 32
        const val OFFSET_X = 40
        const val DEFAULT_OFFSET_X = 20
        const val DEFAULT_OFFSET_Y = 10
    }
}
